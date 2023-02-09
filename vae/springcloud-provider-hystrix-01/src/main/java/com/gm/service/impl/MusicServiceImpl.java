package com.gm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.entity.Music;
import com.gm.dao.MusicMapper;
import com.gm.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
    @Autowired
    private MusicService musicService;
    @Override
    public PageInfo<Music> getMusic(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<Music> list = musicService.list(null);
        return new PageInfo<>(list);
    }
}
