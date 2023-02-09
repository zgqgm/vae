package com.gm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gm.dao.FilmMapper;
import com.gm.entity.Film;
import com.gm.service.FilmService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {

}
