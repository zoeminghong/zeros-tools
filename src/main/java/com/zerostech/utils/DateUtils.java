package com.zerostech.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 迹_Jason on 2017/7/17.
 */
public class DateUtils {

    /**
     * 当日零点
     * @return
     */
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getParseDate(String dte, String pettern) {
        if (StringUtils.isBlank(dte)) {
            throw new DateTimeException("时间不能为空");
        }
        if (StringUtils.isBlank(pettern)) {
            pettern = "YYYY-mm-dd HH:MM:SS";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pettern);
        try {
            return simpleDateFormat.parse(dte);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFormatDate(Date dte, String pettern) {
        if (dte == null) {
            throw new DateTimeException("时间对象不能为空");
        }
        if (StringUtils.isBlank(pettern)) {
            pettern = "YYYY-mm-dd HH:MM:SS";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pettern);
        return simpleDateFormat.format(dte);
    }

    /**
     * 获取某年第一天日期
     *
     * @param dte
     * @return Date
     */
    public static Date getYearFirst(Date dte) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dte);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param dte
     * @return Date
     */
    public static Date getYearLast(Date dte) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dte);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DATE, 31);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 是否是同一年
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isSameYear(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(end);
        return startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR);
    }

    /**
     * 是否大于一年
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isOverOneYear(Date start, Date end) {
        long dte = end.getTime() - start.getTime();
        return dte /(1000 * 60 * 60 * 24)>365;
    }
}
