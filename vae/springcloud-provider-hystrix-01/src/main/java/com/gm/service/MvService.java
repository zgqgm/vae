package com.gm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.gm.entity.Mv;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
public interface MvService extends IService<Mv> {
    PageInfo<Mv> getMv(Integer pageNum, Integer size);
}
