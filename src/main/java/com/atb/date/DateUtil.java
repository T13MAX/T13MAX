package com.atb.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转日期时间
 *
 * @Author 呆呆
 * @Datetime 2021/11/15 14:19
 */
public class DateUtil {
    public static void main(String[] args) {
        Date date = new Date(1640745681678L);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.format(date));
    }
}
