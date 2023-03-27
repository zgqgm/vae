package com.gm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gm.entity.IpLog;
import com.gm.entity.entity_2.User;
import com.gm.service.ErrorIpService;
import com.gm.service.UserService;
import com.gm.util.GetRequestIpUtil;
import com.gm.util.JwtUtil;
import com.gm.util.MailConfigurationUtil;
import com.gm.util.TimeUtil;
import com.gm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private ErrorIpService ipService;
    @Autowired
    private MailConfigurationUtil mailConfigurationUtil;

    //后续需要可以提供赋值接口
    //默认错误次数
    public final int errNums = 5;
    //锁定时间（单位：小时 默认24小时）
    public final int minute = 24;

    /**
     * 获取微信登录凭证
     * */
    @GetMapping("/user/code")
    public ResultVO getCode(String code) {
        String appid = "wx**************38f";
        //app秘钥
        String appSecret = "133ced039******************f8a49";
        if (code == null || code.equals("")){
            return ResultVO.error("code为空");
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code +"&grant_type=authorization_code";
        return ResultVO.success(url);
    }

    /**
     * 微信注册
     * @param user 用户信息
     * @return ResultVO
     */
    @PostMapping("/user/logon")
    public ResultVO logon(@RequestBody User user) {
        if (user.getOpenId()==null || user.getOpenId().equals("")){
            return ResultVO.error("非法注册");
        }
        //如果没有用户名信息，就生成一个（微信一定有）
        if(user.getName().equals("") || user.getName() == null){
            user.setName(String.valueOf(user.getOpenId().hashCode()));
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openId",user.getOpenId());
        User one = userService.getOne(userQueryWrapper);
        if (one == null){
            return userService.addUser(user);
        }
        return ResultVO.error("当前账号已存在，注册失败");
    }

    /**
     *通过微信登录
     * @param openId 用户的唯一标识
     * @return ResultVo
     */
    @RequestMapping("/user/login")//get明文传输--危险
    public ResultVO login(String openId){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openId",openId);
        System.out.println(openId);
        User user = userService.getOne(userQueryWrapper);
        if (user != null){
            return ResultVO.success(user);
        }
        return ResultVO.error("未注册");
    }
    /**
     * 账号登录-------临时的，后续与微信登录等合并，减少代码冗余
     * 以后操作数据库写在serviceImpl里，注意MVC格式
     */
    @PostMapping("/user/login")
    public ResultVO login(String username, String password, String code, HttpServletRequest request) throws ParseException {//现在先不用code
        Date date = new TimeUtil().getCurrentTime_1();
        //获取ip
        String ip = new GetRequestIpUtil().GetRequestIp(request);
        IpLog ipInfo = ipService.selectIpAll(ip);
        boolean legalization = false;
        if (ipInfo != null && ipInfo.getNums() >= this.errNums){
            if ((date.getTime() - ipInfo.getErrorTime().getTime())/3600000 < this.minute){
                return ResultVO.error("您的ip在"+this.minute+"小时内尝试了5次错误登录，现已对您的ip进行了锁定。\n请在"+this.minute+"小时后再次尝试。");
            }else {
                //ip以合法化，可以删除对该ip的记录，但不确定本次请求是否有效，不予立即清除记录
                legalization = true;
            }

        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userService.getOne(userQueryWrapper);
        if (user == null){
            return ResultVO.error("账号不存在");
        }
        if (user.getNums() >= this.errNums){
            if ((date.getTime() - user.getErrorTime().getTime())/3600000 <this.minute ){
                return ResultVO.error("账号 "+user.getName()+" 在"+this.minute+"小时内尝试了"+this.errNums+"次的错误登录，现已被锁定。\n请在"+this.minute+"小时后再次尝试。");
            }
            user.setNums(0);
            user.setErrorIpId(0);
        }

            //比较密码
            if(user.getPassword().equals(password)){//登录成功
                //设置token参数
                Map<String, Object> map = new HashMap<>();
                map.put("auth",user.getName());

                //移除合法化ip记录
                if (legalization){
                    ipService.removeIp(ipInfo.getId());
                }
                //重置试错机会
                if (user.getNums() != 0){
                    user.setNums(0);
                }
                //设置登录ip
                if (user.getUserIp().equals("")){
                    user.setUserIp(ip);
                }else if (!user.getUserIp().equals(ip)){
                    if (user.getMail() != null && !user.getMail().equals("")){
                        String content = "<div>\n" +
                                "\t\t\t【Vae官方】\t提醒：\n"+
                                "\t\t\t\t\t检测到您的账号当前可能存在异地登录，登录方式为账号登录。若非本人操作，请及时修改密码，以确保账号安全。"+
                                "\t\t</div>";
                        mailConfigurationUtil.getMyMail()
                                .setTo(user.getMail())
                                .setContent(content)
                                .setSubject("提醒")
                                .go();
                    }
                    user.setUserIp(ip);
                }
                //设置登录时间
                user.setLoginTime(date);
                ResultVO resultVO = userService.upDataInfo(user);
                //获取token--过期时间2H---签名先用固定值，之后改随机值
                String token = JwtUtil.getToken(map,"qwertIIoI12.wIp^9m^",1000*60*60*2L);

                resultVO.add("userInfo",user);
                resultVO.add("token",token);
                //存入session/token中
                return resultVO;
            }else {//登录失败
                //记录非法ip
                int ipNums = -1;
                if (legalization){
                    ipNums = 0;
                }else if (ipInfo != null){
                    ipNums = ipInfo.getNums();
                }
                IpLog ipLog = ipService.recordIp(ip,date,ipNums);

                //就向用户添加非法操作信息（只记录最后一次错误登录ipId）
                int ipId = 0;
                if (ipNums == -1){
                    ipId = ipLog.getId();
                }else if (ipInfo.getId() != user.getErrorIpId()){
                    ipId = ipInfo.getId();
                }
                userService.recordIp(user.getId(),date,user.getNums()+1,ipId);

                if ((user.getNums()+1) >= this.errNums){
                    System.out.println("您的账号在"+this.minute+"小时内尝试了"+this.errNums+"次错误登录，现已对您的账号进行了锁定。\n请在"+this.minute+"小时后再次尝试。");
                    return ResultVO.error("账号 "+user.getName()+" 在"+this.minute+"小时内尝试了"+this.errNums+"次的错误登录，现已被锁定。\n请在"+this.minute+"小时后再次尝试。");
                }
                if (ipLog.getNums() >= this.errNums){
                    System.out.println("您的ip在"+this.minute+"小时内尝试了"+this.errNums+"次错误登录，现已对您的ip进行了锁定。\n请在"+this.minute+"小时后再次尝试。");
                    return ResultVO.error("您的ip在"+this.minute+"小时内尝试了"+this.errNums+"次错误登录，现已对您的ip进行了锁定。\n请在"+this.minute+"小时后再次尝试。");
                }
                return ResultVO.error("账号或密码错误\n已记录您的ip:"+ip+"\n您还有"+(this.errNums - ipLog.getNums())+"次试错机会");
            }
    }

    //定时清理数据库--------该功能先不写了，定时器太麻烦
    /**
     * 更新用户信息
     * @param user 用户信息
     */
    @PutMapping("/user/info")
    public ResultVO upData(@RequestBody User user){
        if(user.getOpenId() == null || user.getOpenId().equals("")){
            return ResultVO.error("未登录，请先登录再次操作");
        }
        return userService.upDataInfo(user);
    }

    /**
     * 微信用户退出
     * @param openId 用户的唯一标识
     * @return 成败
     */
    @GetMapping("/user/logout")
    public ResultVO logout(String openId){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openId",openId);
        boolean remove = userService.remove(userQueryWrapper);
        if (remove){
            return ResultVO.success("删除成功");
        }
        return ResultVO.error("删除失败");
    }


}

