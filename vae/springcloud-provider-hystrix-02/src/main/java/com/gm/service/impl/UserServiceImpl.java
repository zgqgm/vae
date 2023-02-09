package com.gm.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gm.dao.UserMapper;
import com.gm.entity.entity_2.User;
import com.gm.service.UserService;
import com.gm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO addUser(User user) {
        int insert = userMapper.insert(user);
        if (insert == 0){
            return ResultVO.error("注册失败，请稍后再试");
        }
        return ResultVO.success(user);
    }

    @Override
    public ResultVO upDataInfo(User user) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        if (!user.getName().equals("") || user.getName() != null){
            userUpdateWrapper.eq("name",user.getName());
        }else if (!user.getOpenId().equals("") || user.getOpenId() != null){
            userUpdateWrapper.eq("openId",user.getOpenId());
        }
        int update = userMapper.update(user, userUpdateWrapper);
        if (update == 0){
            return ResultVO.error("修改失败，请稍后重新修改");
        }
        return ResultVO.success(update);
    }

    /**
     * 向用户表中记录账号登录失败相关的信息
     * @param id 用户ID
     * @param date 当前时间
     * @param nums 登录失败次数
     * @param ipId 登录失败的请求者ip所对应的id
     */
    @Override
    public void recordIp(Integer id, Date date, int nums, int ipId) {
        userMapper.recordIpInfo(id,date,nums,ipId);
    }
}
