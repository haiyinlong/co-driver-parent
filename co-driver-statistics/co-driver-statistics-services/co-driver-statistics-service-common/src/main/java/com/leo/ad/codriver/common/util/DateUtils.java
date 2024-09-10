/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.leo.ad.codriver.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_YYYYMMMDD = "yyyyMMdd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        Instant instant = date.toInstant();
        return Date.from(instant.plusSeconds(minutes * 60L));
    }

    public static Integer getNowDates() {
        return Integer
                .valueOf((LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD))));
    }

    /**
     * 返回前一天的日期
     *
     * @return
     */
    public static Integer getPreviousDate() {
        return getPreviousDate(1);
    }

    /**
     * 前几天的日期
     *
     * @param day 前几天，正数： 1，2，3
     * @return 20240112
     */
    public static Integer getPreviousDate(int day) {
        return Integer.valueOf((LocalDateTime.now().plusDays(-1 * day)
                .format(java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD))));
    }

    public static Integer getAfterDay(Integer dates, int day) {
        LocalDate localDate = LocalDate.parse(dates + "", java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD));
        return Integer
                .valueOf(localDate.plusDays(day).format(java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD)));
    }

    public static List<Integer> getDates(Integer dates, int withinDay) {
        LocalDate localDate = LocalDate.parse(dates + "", java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD));
        List<Integer> withinDates = new ArrayList<>();
        boolean isPlus = withinDay > 0;
        for (int i = 0; i < Math.abs(withinDay); i++) {
            Integer value = Integer.valueOf(localDate.plusDays(isPlus ? i : -i)
                    .format(java.time.format.DateTimeFormatter.ofPattern(DATE_YYYYMMMDD)));
            withinDates.add(value);
        }

        return withinDates;
    }

}
