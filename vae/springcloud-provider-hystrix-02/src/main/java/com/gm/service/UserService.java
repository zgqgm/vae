package com.gm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gm.entity.entity_2.User;
import com.gm.vo.ResultVO;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-08-01
 */
public interface UserService extends IService<User> {
    ResultVO addUser(User user);

    ResultVO upDataInfo(User user);

    void recordIp(Integer id, Date date, int nums, int ipId);
}
