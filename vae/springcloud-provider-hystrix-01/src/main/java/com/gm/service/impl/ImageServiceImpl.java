package com.gm.service.impl;

import com.gm.entity.Image;
import com.gm.dao.ImageMapper;
import com.gm.service.ImageService;
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
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

}
