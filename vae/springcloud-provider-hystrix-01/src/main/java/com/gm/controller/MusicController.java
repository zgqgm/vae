package com.gm.controller;


import com.github.pagehelper.PageInfo;
import com.gm.entity.Music;
import com.gm.vo.ResultVO;
import com.gm.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/musicList")
    public ResultVO getMusic(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size){

        PageInfo<Music> music = musicService.getMusic(pageNum,size);

        return ResultVO.success(music);
    }
}
