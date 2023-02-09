package com.gm.controller;

import com.gm.service.MailService;
import com.gm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "getCode",method = RequestMethod.POST)
    public ResultVO getCode(String to, HttpServletRequest request, HttpServletResponse response){
        return mailService.getCode(to, request, response);
    }

    @RequestMapping(value = "mailLogin",method = RequestMethod.POST)
    public ResultVO mailLogin(String mail, String code,HttpServletRequest request, HttpServletResponse response){
        return mailService.mailLogin(mail,code,request,response);
    }

}
