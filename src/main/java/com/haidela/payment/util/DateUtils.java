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
     * 获取指定日期yyyy-MM-dd HH:mm:ss 的工具信息
     *
     * @param
     * @return
     */
    public static String stringToDate() {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
        return df.format(new Date());
    }

}
