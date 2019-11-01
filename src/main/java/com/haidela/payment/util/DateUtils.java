package com.haidela.payment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author zhanglize
 * @create 2019/10/11
 */
public class DateUtils {

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间格式：HH:mm
     */
    public static final String DEFAULT_TIME_PATTERN = "HH:mm";

    public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");//年份格式
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");//日期格式
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");//时间时间
    public static final SimpleDateFormat DATE_AND_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//完整日期加时间格式
    public static final long WEEK_NOW_CHINESE = getChieseWhicthDay(new Date()); //今天星期几,中国习惯的
    public static final Date NOW_DATE = new Date();//当前时间

    /**
     * 查当前日期是一周中的星期几
     *
     * @return 1=Sunday,,,7=Saturday
     */
    public static long getWhicthDay(Date today) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_WEEK);// 1=Sunday,,,7=Saturday
    }

    /**
     * 获得符合中国习惯的星期几
     *
     * @param theDate
     * @return
     */
    public static long getChieseWhicthDay(Date theDate) {
        long week = getWhicthDay(theDate);
        if (week == Calendar.SUNDAY) return 7;
        else return week - 1;
    }

    /**
     * 获取某个日期对应的星期几
     *
     * @param theDate
     * @param week    星期数 1为星期日,类推
     * @return Date
     */
    public static Date whicthDayOfSomeDate(Date theDate, int week) {
        if (week == 1) return getSundayOfThisWeek(theDate); //如果求某个日期对应的星期日,调用星期日的逻辑
        Calendar c = Calendar.getInstance();
        if (theDate != null) c.setTime(theDate);
        c.set(Calendar.DAY_OF_WEEK, week); // 获取 周一
        if (getWhicthDay(theDate) == 1) { //输入日期为星期日
            c.add(Calendar.DATE, -7);
        }
        return c.getTime();
    }

    /**
     * 判断是否对应的日期格式
     *
     * @param time
     * @return
     */
    public static boolean judgeFormat(String time) {
        boolean flag = true;
        try {
            DATE_AND_TIME_FORMAT.parse(time);
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 获取某个日期对应的 日期 的周日
     *
     * @param theDate 某个日期 null的话，表示当前日期
     *                获取当前日期的周7,注意 日历获取的周期是
     *                周日,1,2,3,4,5,6  不是中国传统的
     *                1,2,3,4,5,6,周日
     *                所以要加7
     * @return Date
     */
    public static Date getSundayOfThisWeek(Date theDate) {
        Calendar c = Calendar.getInstance();
        if (theDate != null) c.setTime(theDate);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);               // 获取 周日
        c.add(Calendar.DAY_OF_MONTH, 7);     // 获取 中国意义上的 周日
        if (getWhicthDay(theDate) == 1) {  //输入日期为星期日
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - 8);
        }
        return c.getTime();
    }

    /**
     * 字符串转换为日期类型
     * string-->yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString) {
        try {
            return DATE_AND_TIME_FORMAT.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 日期类型转化为字符串
     * yyyy-MM-dd HH:mm:ss -->string
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        try {
            return DATE_AND_TIME_FORMAT.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date.toString();
    }

    /**
     * yyyy-MM-dd HH:mm:ss --->yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String timeToDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss --->HH:mm
     *
     * @param date
     * @return
     */
    public static String dateToOnlyTime(Date date) {
        return TIME_FORMAT.format(date);
    }

    /**
     * yyyy-MM-dd+HH:mm:ss = yyyy-MM-dd HH:mm:ss
     *
     * @param date yyyy-MM-dd
     * @param time HH:mm:ss
     * @return
     */
    public static Date groupDateAndTime(Date date, Date time) {
        Calendar c1 = Calendar.getInstance();//yyyy-MM-dd
        c1.setTime(date);
        Calendar c2 = Calendar.getInstance();//HH:mm:ss
        c2.setTime(time);
        Calendar group = Calendar.getInstance();//yyyy-MM-dd HH:mm:ss
        group.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        group.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        group.set(Calendar.DATE, c1.get(Calendar.DATE));
        group.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR));
        group.set(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        group.set(Calendar.SECOND, c2.get(Calendar.SECOND));
        String s = DATE_AND_TIME_FORMAT.format(group.getTime()); //经过一次转化为标准格式的处理
        try {
            return DATE_AND_TIME_FORMAT.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期yyyy-MM-dd HH:mm:ss 的工具信息
     *
     * @param
     * @return
     */
    public static String stringToDate() {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        return df.format(new Date());
    }

    /**
     * 获取指定日期HH:mm 的工具信息
     *
     * @param
     * @return
     */
    public static String nowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return df.format(new Date());
    }


}
