package com.gm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gm.dao.ErrorIpMapper;
import com.gm.entity.IpLog;
import com.gm.service.ErrorIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ErrorIpServiceImpl extends ServiceImpl<ErrorIpMapper, IpLog> implements ErrorIpService {
    @Autowired
    private ErrorIpMapper ipMapper;

    /**
     * 查询是否已存储该ip，如果存在。那么就nums+1
     * 否则就插入该ip，nums=1
     * @param ip 非法ip
     * @return 尝试次数
     */
    @Override
    public IpLog recordIp(String ip, Date date,int nums) {
        IpLog ipLog = new IpLog();
        if (nums == -1){//说明没有ip，需要创建
            ipLog.setErrorIp(ip);
            ipLog.setErrorTime(date);
            ipMapper.insert(ipLog);
            return ipMapper.selectIpAll(ip);
        }else {
            ipMapper.updateNums(nums+1,ip,date);
            ipLog.setNums(nums+1);
            return ipLog;
        }
    }

    /**
     * 查询当前ip是否被记录，记录次数为多少。
     * @param ip 请求ip
     * @return 记录次数
     */
    @Override
    public IpLog selectIpAll(String ip) {
        return ipMapper.selectIpAll(ip);
    }

    /**
     * 移除ip记录
     * @param id ipId
     * @return 移除成功1，失败0
     */
    @Override
    public int removeIp(Integer id) {
        return ipMapper.deleteById(id);
    }
}
