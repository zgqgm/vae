package com.gm.fallbackfactory;

import com.gm.entity.entity_2.User;
import com.gm.service.ClientService;
import com.gm.vo.ResultVO;
import feign.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import feign.hystrix.FallbackFactory;

/**
 * 服务器降级
 */
@Component
public class DeptClientServiceFallBackFactory{// implements FallbackFactory {
    //@Override
    public ClientService create(Throwable throwable) {
        //匿名内部类实现接口
        return new ClientService() {
            //------------------music start------------------------------------
            @Override
            public ResultVO getMusic(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size) {
                return null;
            }
            //------------------music end------------------------------------
            //------------------mv start------------------------------------
            @Override
            public ResultVO getMv(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size) {
                return null;
            }
            //------------------mv end------------------------------------
            //------------------login start------------------------------------
            @Override
            public ResultVO getCode(@RequestParam(value = "code") String code){
                return null;
            }

            @Override
            public ResultVO logon(@RequestBody User user) {
                return null;
            }

            @Override
            public ResultVO login(String openId) {
                return null;
            }

            @Override
            public ResultVO upData(User user) {
                return null;
            }

            @Override
            public ResultVO logout(String openId) {
                return null;
            }

            @Override
            public ResultVO login1(String username, String password, String code, HttpServletRequest request) {
                return null;
            }

            //邮箱登录
            @Override
            public ResultVO getCode(String to, HttpServletRequest request, HttpServletResponse response){
                return null;
            }

            @Override
            public ResultVO mailLogin(String mail, String code, HttpServletRequest request, HttpServletResponse response){
                return null;
            }

            //------------------login end------------------------------------
            //------------------chat start------------------------------------
            @Override
            public ResultVO chat() {
                return null;
            }

            //------------------chat end------------------------------------
            /*@Override
            public String test() {
                return null;
            }

            @Override
            public PoJo testCB(Integer id) {
                PoJo poJo = new PoJo();
                poJo
                        .setId(0)
                        .setMessage("服务器降级！....");
                return poJo;
            }*/
        };
    }
}
