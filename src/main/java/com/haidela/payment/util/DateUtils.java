package com.haidela.payment.util;

import java.text.SimpleDateFormat;
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
