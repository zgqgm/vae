package com.gm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /**
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @return util.Date类型
     */
    public Date getCurrentTime_1(){
        Date parse = null;
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            parse = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取当前的时间戳
     * @return 时间戳
     */
    public long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 将时间戳转为指定时间的格式
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @param timeMillis 要转换的时间戳
     * @return 时间格式化后的 util.Date
     */
    public Date getTime_1(long timeMillis) throws ParseException {
        Date date = new Date(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        System.out.println(time);
        return format.parse(time);
    }
}
