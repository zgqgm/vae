package com.gm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gm.dao.MvMapper;
import com.gm.entity.Mv;
import com.gm.service.MvService;
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
public class MvServiceImpl  extends ServiceImpl<MvMapper, Mv> implements MvService {
    @Autowired
    private MvMapper mvMapper;
    @Override
    public PageInfo<Mv> getMv(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<Mv> mvs = mvMapper.selectList(null);
        PageInfo<Mv> mvPageInfo = new PageInfo<>(mvs);
        return mvPageInfo;
    }
}
