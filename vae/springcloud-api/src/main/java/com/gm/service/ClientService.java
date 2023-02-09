package com.gm.service;

import com.gm.entity.entity_2.User;
import com.gm.fallbackfactory.DeptClientServiceFallBackFactory;
import com.gm.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @FeignClient:微服务客户端注解,value:指定微服务的名字,这样就可以使Feign客户端直接找到对应的微服务
@FeignClient(value = "SPRINGCLOUD-PROVIDER",fallbackFactory = DeptClientServiceFallBackFactory.class)//fallbackFactory指定降级配置类
@Service
public interface ClientService {
    //------------------music start------------------------------------
    @RequestMapping("/music/musicList")
    public ResultVO getMusic(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size);
    //------------------music end------------------------------------

    //------------------mv start------------------------------------
    @RequestMapping("/mv/mvList")
    public ResultVO getMv(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size);
    //------------------mv end------------------------------------

    //------------------login start------------------------------------
    @RequestMapping("/user/code")
    public ResultVO getCode(@RequestParam(value = "code") String code);

    @RequestMapping("/user/logon")
    public ResultVO logon(@RequestBody User user);

    @RequestMapping("/user/login")
    public ResultVO login(@RequestParam(value = "openId")String openId);

    @RequestMapping(value = "/user/info",method = RequestMethod.PUT)
    public ResultVO upData(@RequestBody User user);

    @RequestMapping("/user/logout")
    public ResultVO logout(@RequestParam(value = "openId") String openId);

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    ResultVO login1(@RequestParam String username,@RequestParam String password,@RequestParam String code,@RequestParam HttpServletRequest request);

    //邮箱登录
    @RequestMapping(value = "/mail/getCode",method = RequestMethod.POST)
    ResultVO getCode(@RequestParam String to, @RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response);

    @RequestMapping(value = "/mail/mailLogin",method = RequestMethod.POST)
    ResultVO mailLogin(@RequestParam String mail,@RequestParam String code,@RequestParam HttpServletRequest request,@RequestParam HttpServletResponse response);

    //------------------login end------------------------------------
    //------------------chat start------------------------------------
    @PostMapping("/user/chat")
    ResultVO chat();


    //------------------chat end------------------------------------
    /*@GetMapping("/test/db")
    public String test();

    @GetMapping("/test/HCB/{id}")
    public PoJo testCB(@PathVariable(value = "id") Integer id);*/
}
