package io.sofastack.cloud.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 5:42 PM
 * @since:
 **/
public class DateUtils {
    /**
     * 获取今天零点
     *
     * @return
     */
    public static Date getLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取昨天零点
     *
     * @return
     */
    public static Date getPreDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
