package com.gm.service;

import com.github.pagehelper.PageInfo;
import com.gm.entity.Music;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
public interface MusicService extends IService<Music> {

    PageInfo<Music> getMusic(Integer pageNum, Integer size);
}
