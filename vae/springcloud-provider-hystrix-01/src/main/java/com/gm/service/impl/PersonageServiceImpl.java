package com.gm.service.impl;

import com.gm.entity.Personage;
import com.gm.dao.PersonageMapper;
import com.gm.service.PersonageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PersonageServiceImpl extends ServiceImpl<PersonageMapper, Personage> implements PersonageService {

}
