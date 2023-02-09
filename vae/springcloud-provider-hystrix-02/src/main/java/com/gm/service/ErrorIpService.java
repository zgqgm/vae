package com.gm.service;

import com.gm.entity.IpLog;

import java.util.Date;

public interface ErrorIpService {
    IpLog recordIp(String ip, Date date,int nums);

    IpLog selectIpAll(String ip);

    int removeIp(Integer id);
}
