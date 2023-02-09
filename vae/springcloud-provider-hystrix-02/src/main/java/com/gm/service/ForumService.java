package com.gm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gm.entity.UserChat;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-08-01
 */
public interface ForumService extends IService<UserChat> {
    int addChat(HashMap<String, String> map);
}
