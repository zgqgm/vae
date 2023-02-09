package com.gm.controller;

import com.github.pagehelper.PageInfo;
import com.gm.entity.Mv;
import com.gm.vo.ResultVO;
import com.gm.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
@RestController
@RequestMapping("/mv")
public class MVController {
    @Autowired
    private MvService mvService;

    @GetMapping("/mvList")
    public ResultVO getMusic(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "size") Integer size){

        PageInfo<Mv> mv = mvService.getMv(pageNum,size);

        return ResultVO.success(mv);
    }
}
