package com.yhr.course.course.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/25
 */
public class DateUtils {

    public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static List<String> getDayBetween(Integer days) {
        List<String> result = new ArrayList<>();
        for (int i = days - 1; i >= 0; i--) {
            Calendar c = Calendar.getInstance();
            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, Integer.parseInt("-" + i));
            Date d = c.getTime();
            String day = format.format(d);
            result.add(day);
        }
        return result;
    }
}
