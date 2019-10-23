package com.haidela.payment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Excel 工具类
 *
 * @author zhanglize
 * @create 2019/10/22
 */
public class ExcelUtils {

    /**
     * Excel 2003的正则匹配字符
     */
    private static final Pattern EXCEL_ZEROTHREE_REGEX = Pattern.compile("^+\\.(?i)(xls)$");
    /**
     * Excel 2007的正则匹配字符
     */
    private static final Pattern EXCEL_ZEROSEVEN_REGEX = Pattern.compile("^+\\.(?i)(xlsx)$");
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public ExcelUtils() {
    }

    /**
     * 传入文件名，判断文件是否为 2003的Excel
     *
     * @param filePath
     * @return
     */
    private static boolean isExcel2003(String filePath) {
        return EXCEL_ZEROTHREE_REGEX.matcher(filePath).matches();
    }

    /**
     * 传入文件名，判断文件是否为 2007的Excel
     *
     * @param filePath
     * @return
     */
    private static boolean isExcel2007(String filePath) {
        return EXCEL_ZEROSEVEN_REGEX.matcher(filePath).matches();
    }

    /**
     * 判断该value值对用的数据
     *
     * @param t
     * @param name
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> getValue(T t, String name) {
        Map<String, String> map = new HashMap<>();
        Class<T> tClass = (Class<T>) t.getClass();
        String string = null;
        Field[] fields = tClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(name)) {
                fields[i].setAccessible(true);
                try {
                    if (fields[i].get(t) != null) {
                        //获取字段对应的属性值
                        string = fields[i].get(t).toString();
                        map.put(string, fields[i].getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }


//    /**
//     * 创建表格数据
//     *
//     * @param list  读取的数据
//     * @param sheet Excel的表明
//     * @param row   行
//     * @param cell  单元格
//     */
//    public static void createTable(List<Object> list, Sheet sheet, Row row, Cell cell) {
//
//    }


}
