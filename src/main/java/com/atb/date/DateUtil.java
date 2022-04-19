package com.atb.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间戳转日期时间
 *
 * @Author 呆呆
 * @Datetime 2021/11/15 14:19
 */
public class DateUtil {
    public static void main(String[] args) {
        Date date = new Date(1649759456000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.format(date));

        System.out.println(getEatTime());
    }

    private static long getEatTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR, 6);
        instance.set(Calendar.MINUTE, 30);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime().getTime();
    }
}
