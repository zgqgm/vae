package com.gm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gm.dao.UserMapper;
import com.gm.entity.IpLog;
import com.gm.entity.entity_2.User;
import com.gm.service.ErrorIpService;
import com.gm.service.MailService;
import com.gm.service.UserService;
import com.gm.util.GetRequestIpUtil;
import com.gm.util.JwtUtil;
import com.gm.util.MailConfigurationUtil;
import com.gm.util.TimeUtil;
import com.gm.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailConfigurationUtil mailConfigurationUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ErrorIpService ipService;
    @Autowired
    private UserService userService;

    //后续需要可以提供赋值接口
    //默认错误次数
    public final int errNums = 5;
    //锁定时间（单位：小时 默认24小时）
    public final int minute = 24;

    @Override
    public ResultVO getCode(String to, HttpServletRequest request, HttpServletResponse response) {
        if (to == null || to.equals("")){
            return ResultVO.error("请先输入邮箱后，再次尝试");
        }
        HttpSession session = request.getSession(false);
        log.error("session:{}",session);
        if (session == null){
            session = request.getSession(true);

            //这里不使用Cookie对象设置cookie，而是使用ResponseCookie对象。原因：设置sameSite=None,解决跨域浏览器不写入cookie问题
            ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId())
                    .httpOnly(false)// true禁止js读取,可防止xss
                    .maxAge(Duration.ofMinutes(10))//有效期10分钟
                    .path("/")
                    .domain("localhost")//允许域
                    //.sameSite("None")
                    //.secure(true)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }else{
            Integer errorNum = (Integer)session.getAttribute("userErrorNum");
            Integer errorIp = (Integer)session.getAttribute("userErrorIp");
            if (errorIp != null || errorNum != null){
                if (errorNum >= this.errNums){
                    return ResultVO.error("该邮箱在"+this.minute+"小时内尝试了"+this.errNums+"次登录，现已被锁定。\n请在"+this.minute+"小时后再次尝试。");
                }
                if(errorIp >= this.errNums){
                    return ResultVO.error("您在"+this.minute+"小时内尝试了"+this.errNums+"次登录，现已被锁定。\n请在"+this.minute+"小时后再次尝试。");
                }
            }

        }
        //======================================================================
        //获取当前时间
        Date date = new TimeUtil().getCurrentTime_1();
        //获取ip
        String ip = new GetRequestIpUtil().GetRequestIp(request);
        String ipSession = (String) session.getAttribute("ip");
        IpLog ipInfo = (IpLog) session.getAttribute("ipInfo");

        if (ipInfo == null || (ipSession ==null || !ip.equals(ipSession ))){
            ipInfo = ipService.selectIpAll(ip);
        }


        boolean legalization = false;
        //防止session失效后，记录用户再次尝试获取验证码--并判断是否合法化
        if (ipInfo != null && ipInfo.getNums() >= this.errNums){
            if ((date.getTime() - ipInfo.getErrorTime().getTime())/3600000 < this.minute){
                return ResultVO.error("您在"+this.minute+"小时内尝试了5次错误登录。\n请在"+this.minute+"小时后再次尝试。");
            }
            //ip以合法化，可以删除对该ip的记录，但不确定本次请求是否有效，不予立即清除记录
            legalization = true;
        }
        User userSession = (User) session.getAttribute("user");
        User user = null;
        if (userSession != null && userSession.getMail().equals(to)){
            user = userSession;
        }else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("mail",to);
            user = userMapper.selectOne(wrapper);
            if (user == null){
                //后续添加自动注册------------------------------------------------》》》》》》》》》》》》
                return ResultVO.error("邮箱不存在");
            }
        }

        //防止session失效后，记录账号再次尝试获取验证码--并判断是否合法化
        if (user.getNums() >= this.errNums){
            if ((date.getTime() - user.getErrorTime().getTime())/3600000 <this.minute ){
                return ResultVO.error("账号 "+user.getName()+" 在"+this.minute+"小时内尝试了"+this.errNums+"次的错误登录，现已被锁定。\n请在"+this.minute+"小时后再次尝试。");
            }
            user.setNums(0);
        }

        //记录ip
        int ipNums = -1;

        if (legalization){
            //合法化后记录归零
            ipNums = 0;
        }else if (ipInfo != null){
            //否则获取记录次数
            ipNums = ipInfo.getNums();
        }
        //若没有记录则按-1记录
        //ipService.recordIp -- 对记录加一后插入或修改数据（插入数据会返回新对象，修改不会）
        IpLog ipLog = ipService.recordIp(ip,date,ipNums);

        //就向用户添加非法操作信息（只记录最后一次错误登录ipId）
        int ipId = 0;
        //ipInfo为null时ipNums==-1
        if (ipNums == -1){
            //插入数据后，获取新数据id
            ipId = ipLog.getId();
        }else {
            //否则记录本次查询ip的id
            ipId = ipInfo.getId();
        }
        //更新数据
        Integer nums = user.getNums();
        user.setNums(nums+1);
        user.setErrorTime(date);
        user.setErrorIpId(ipId);
        if (ipInfo == null){
            ipInfo = ipLog;
        }else {
            ipInfo.setErrorTime(date);
            ipInfo.setNums(ipNums+1);
        }
        //记录修改数据
        userService.recordIp(user.getId(),date,user.getNums(),ipId);

        //存入session信息
        session.setAttribute("userErrorNum",(user.getNums()));
        session.setAttribute("userErrorIp",ipInfo.getNums());
        session.setAttribute("currentNum",0);
        session.setAttribute("userInfo",user);
        session.setAttribute("ipInfo",ipInfo);
        session.setAttribute("ip",ip);
        //======================================================================
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }


        session.setAttribute("createTime",System.currentTimeMillis());
        session.setAttribute("code",str.toString());
        String content ="<div>\n" +
                "\t\t\t【Vae官方】\t验证码：<span id=\"code\" style=\"color: #025aff;border-bottom: 1px solid #025aff;\" onclick=\"copy()\">"+str.toString()+"</span>。你正在使用邮箱验证码登录，有效期10分钟。为保护您的账号安全，请勿将验证码提供给他人，谨防上当受骗。若非本人操作，请忽略。\n" +
                "\t\t</div>";
        boolean go = mailConfigurationUtil.getMyMail()
                .setTo(to)
                .setContent(content)
                .setSubject("验证码")
                .go();
        if (go){
            return ResultVO.success("验证码发送成功");
        }
        return ResultVO.success("验证码发送失败，请重新尝试");
    }

    @Override
    public ResultVO mailLogin(String mail, String code, HttpServletRequest request, HttpServletResponse response) {
        //拒绝空字符参数请求
        if ((mail == null || mail.equals(""))||(code == null || code.equals(""))){
            return ResultVO.error("邮箱或验证码不能为空");
        }
        //获取session对象
        HttpSession session = request.getSession(false);

        //非空判断
        if (session == null){
            return ResultVO.error("请先获取验证码后,再尝试登录");
        }

        Long times= (Long) session.getAttribute("createTime");
        Integer currentNum = (Integer) session.getAttribute("currentNum");

        if (currentNum == null || currentNum >= this.errNums){
            return ResultVO.error("请稍后再试");
        }
        if (times == null){
            return ResultVO.error("请先获取验证码后,再尝试登录");
        }


        //拒绝超时请求
        if (((System.currentTimeMillis() - times)/60000) > 10){
            session.removeAttribute("code");
            session.removeAttribute("createTime");
            return ResultVO.error("验证码过期,请重新获取");
        }
        //获取当前时间
        Date date = new TimeUtil().getCurrentTime_1();

        String codeStr = (String)session.getAttribute("code");
        User user = (User) session.getAttribute("userInfo");
        if (code.equals(codeStr) && mail.equals(user.getMail())){
            //登陆成功
            IpLog ipInfo = (IpLog)session.getAttribute("ipInfo");
            String ip = (String) session.getAttribute("ip");
            //设置token参数
            Map<String, Object> map = new HashMap<>();
            map.put("auth",user.getName());

            //移除合法化ip记录
            if (ipInfo != null ){
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
                            "\t\t\t\t\t检测到您的账号当前可能存在异地登录，登录方式为邮箱登录。若非本人操作，请及时修改密码，以确保账号安全。"+
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
            session.removeAttribute("userErrorNum");
            session.removeAttribute("userErrorIp");
            session.removeAttribute("currentNum");
            session.removeAttribute("userInfo");
            session.removeAttribute("ipInfo");
            session.removeAttribute("ip");
            return resultVO;
        }else {
            //登录失败
            session.setAttribute("currentNum",(currentNum+1));
            return ResultVO.error("验证码错误,请重新获取");
        }


    }
}
