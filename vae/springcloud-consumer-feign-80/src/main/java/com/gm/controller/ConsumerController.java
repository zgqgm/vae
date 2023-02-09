package com.gm.controller;

import com.gm.entity.entity_2.User;
import com.gm.service.ClientService;
import com.gm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController

public class ConsumerController {
    @Autowired
    private ClientService clientService;
    //------------------music start------------------------------------
    @RequestMapping("/music/music")
    public ResultVO getMusic(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size){
        return clientService.getMusic(pageNum,size);
    }
    //------------------music end------------------------------------

    //------------------Mv start--------------------------------------
    @RequestMapping("/mv/mv")
    public ResultVO getMv(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size){
        return clientService.getMv(pageNum,size);
    }
    //------------------mv end------------------------------------
    //------------------login start------------------------------------
    @RequestMapping(value = "/user/code")
    public ResultVO getCode(String code){
        return clientService.getCode(code);
    }

    @RequestMapping(value = "/user/logon")
    public ResultVO logon(@RequestBody User user){
        return clientService.logon(user);
    }

    @RequestMapping(value = "/user/login")
    public ResultVO login(String openId){
        return clientService.login(openId);
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public ResultVO login( String username, String password, String code, HttpServletRequest request){return clientService.login1(username,password,code,request);}

    @RequestMapping(value = "/user/info",method = RequestMethod.PUT)
    public ResultVO upData(@RequestBody User user){
        return clientService.upData(user);
    }

    @RequestMapping(value = "/user/logout")
    public ResultVO logout(String openId){
        return clientService.logout(openId);
    }
    //邮箱登录
    @RequestMapping(value = "/mail/getCode")
    public ResultVO getCode(String to,HttpServletRequest request, HttpServletResponse response){
        return clientService.getCode(to, request, response);
    }

    @RequestMapping(value = "/mail/mailLogin")
    public ResultVO mailLogin(String mail, String code,HttpServletRequest request, HttpServletResponse response){
        return clientService.mailLogin(mail,code,request,response);
    }

    //------------------login end------------------------------------
    //------------------forum start------------------------------------
    @PostMapping("/user/chat")
    public ResultVO chat(){
        return clientService.chat();
    }
    //------------------forum end------------------------------------
    /*@GetMapping("/consumer/db/list")
    public String test(){return "负债均衡之轮询数据库测试：" + clientService.test();}

    @GetMapping("/consumer/hcb/{id}")
    public PoJo testCB(@PathVariable(value = "id") Integer id){
        return clientService.testCB(id);
    }*/
}
