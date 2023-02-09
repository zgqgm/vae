package com.gm.util;

import com.gm.controller.LoginController;
import com.gm.dao.ErrorIpMapper;
import com.gm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.util.Date;


public class TimedTasksUtil {
    @Autowired
    private ErrorIpMapper errorIpMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 每天 0:0:0 执行清理tb_ipLog和tb_userinfo中errorTime小于指定时间的记录
     */
    @Scheduled(cron = "0 0 0 * * ?")//Cron 表达是对应的内容为： seconds minutes hours daysOfMonth months daysOfWeek （具体百度）
    public void ScheduledCleanup() throws ParseException {
        TimeUtil timeUtil = new TimeUtil();
        long timeMillis = timeUtil.getCurrentTimeMillis() - (new LoginController().minute)*60000;
        Date date = timeUtil.getTime_1(timeMillis);
        errorIpMapper.deleteByTime(date);
        int num = userMapper.removeTime(date);

    }
}
