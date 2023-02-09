package com.gm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gm.entity.IpLog;
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
public interface ErrorIpMapper extends BaseMapper<IpLog> {
    int updateNums(@Param("nums") int nums,@Param("ip") String errorIp,@Param("date")Date date);

    IpLog selectIpAll(@Param("ip") String errorIp);

    int deleteByTime(@Param("date")Date date);
}
