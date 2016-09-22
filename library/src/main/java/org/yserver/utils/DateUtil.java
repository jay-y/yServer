package org.yserver.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtil extends DateUtils {
    private static final String DATE_FORMAT = "yyyyMMdd";

    private static String[] parsePatterns = {"yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
            "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     * fns调用
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        } else {
            return DateFormatUtils.format(date, pattern);
        }
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
                * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
                + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的月份数
     *
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceMonthsOfTwoDate(Date before, Date after) {
        Calendar c = Calendar.getInstance();
        c.setTime(before);
        int beforeYear = c.get(Calendar.YEAR);
        int beforeMonth = c.get(Calendar.MONTH);

        c.setTime(after);
        int afterYear = c.get(Calendar.YEAR);
        int afterMonth = c.get(Calendar.MONTH);

        int months;
        if (beforeYear == afterYear) {
            months = afterMonth - beforeMonth;
        } else {
            months = 12 * (afterYear - beforeYear) + afterMonth - beforeMonth;
        }

        return months;
    }

    /**
     * 日期计算
     *
     * @param date
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public static String dateCalOperate(String date, int mYear, int mMonth, int mDay) {
        return dateCalOperate(date, DATE_FORMAT, mYear, mMonth, mDay);
    }

    /**
     * 日期计算
     *
     * @param date
     * @param mYear
     * @param mMonth
     * @param mDay
     * @param isMonthEnd
     * @return
     */
    public static String dateCalOperate(String date, int mYear, int mMonth, int mDay, boolean isMonthEnd) {
        String res = dateCalOperate(date, DATE_FORMAT, mYear, mMonth, mDay);
        if (isMonthEnd) {
            res = getDayOfMonthEnd(res, DATE_FORMAT);
        }
        return res;
    }

    /**
     * 日期计算
     *
     * @param date
     * @param format
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public static String dateCalOperate(String date, String format, int mYear, int mMonth, int mDay) {
        String retDate = "";
        Date sDate = new Date();
        try {
            DateFormat fo = new SimpleDateFormat(format);
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(fo.parse(date));
            tempCal.add(Calendar.YEAR, mYear);
            tempCal.add(Calendar.MONTH, mMonth);
            tempCal.add(Calendar.DATE, mDay);
            sDate = tempCal.getTime();
            retDate = fo.format(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retDate;
    }

    /**
     * 获取月末日期
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDayOfMonthEnd(String date, String dateFormat) {
        String years = "";
        String months = "";
        if (dateFormat.equalsIgnoreCase("yyyyMMdd")) {
            years = date.substring(0, 4);
            months = date.substring(4, 6);
        } else if (dateFormat.equalsIgnoreCase("yyyy-MM-dd")) {
            String[] tmp = date.split("-");
            years = tmp[1];
            months = tmp[2];
        }

        int month = Integer.parseInt(months);
        int year = Integer.parseInt(years);

        int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (year % 4 == 0) {
            if (year % 100 != 0) {
                monthDays[1] = 29;
            }
        }

        if (year % 400 == 0) {
            monthDays[1] = 29;
        }
        String sdate = years + months + monthDays[month - 1];
        return sdate;
    }
}
