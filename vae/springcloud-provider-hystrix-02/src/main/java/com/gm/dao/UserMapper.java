package com.gm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gm.entity.entity_2.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-08-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    int recordIpInfo(@Param("id") Integer id,@Param("date") Date date,@Param("nums") int nums,@Param("ipId") int ipId);

    int removeTime(@Param("date")Date date);
}
