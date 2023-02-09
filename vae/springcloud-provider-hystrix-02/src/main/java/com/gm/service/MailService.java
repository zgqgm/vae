package com.gm.service;

import com.gm.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MailService {
    ResultVO getCode(String to, HttpServletRequest request, HttpServletResponse response);

    ResultVO mailLogin(String mail, String code, HttpServletRequest request, HttpServletResponse response);
}
