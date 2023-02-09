package com.gm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gm.service.ForumService;
import com.gm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    /**
     *
     * @return
     */
    @PostMapping("privately")
    public ResultVO Privately(){
        //分页
        Page<Object> page = new Page<>();
        return null;
    }

   /* public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();

        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("小明","我是小明");
        map.put("小红","我是小红");
        map.put("小王","我是小王");
        map.put("小李","我是小李");
        map.put("小黑","我是小黑");

        int lin = forumService.addChat(map);
    }*/
}
