package com.github.base.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");

    private static final SimpleDateFormat DATE_FORMAT_TIME_YM = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat DATE_FORMAT_TIME_YMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME_MD = new SimpleDateFormat("MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_TIME_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_TIME_MDHM = new SimpleDateFormat("MM-dd HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_TIME_HMS = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_TIME_HM = new SimpleDateFormat("HH:mm");

    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }


    public static String formatDataTime(long date) {
        return DATE_FORMAT_DATETIME.format(new Date(date));
    }

    public static String formatDate(long date) {
        return DATE_FORMAT_DATE.format(new Date(date));
    }
    public static String formatDate_YMDHMS(long date) {
        return DATE_FORMAT_TIME_YMDHMS.format(new Date(date));
    }
    public static String formatDate_YMDHM(long date) {
        return DATE_FORMAT_TIME_YMDHM.format(new Date(date));
    }
    public static String formatDate_YMD(long date) {
        return DATE_FORMAT_TIME_YMD.format(new Date(date));
    }
    public static String formatDate_YM(long date) {
        return DATE_FORMAT_TIME_YM.format(new Date(date));
    }
    public static String formatDate_MD(long date) {
        return DATE_FORMAT_TIME_MD.format(new Date(date));
    }
    public static String formatDate_MDHM(long date) {
        return DATE_FORMAT_TIME_MDHM.format(new Date(date));
    }
    public static String formatDate_HMS(long date) {
        return DATE_FORMAT_TIME_HMS.format(new Date(date));
    }
    public static String formatDate_HM(long date) {
        return DATE_FORMAT_TIME_HM.format(new Date(date));
    }

    public static String formatTime(long date) {
        return DATE_FORMAT_TIME.format(new Date(date));
    }

    public static String formatDateCustom(String beginDate, String format) {
        return new SimpleDateFormat(format).format(new Date(Long.parseLong(beginDate)));
    }

    public static String formatDateCustom(Date beginDate, String format) {
        return new SimpleDateFormat(format).format(beginDate);
    }

    public static Date string2Date(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 6) {
            return null;
        }
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
    }
    public static String getDateTime(){
        return DATE_FORMAT_DATETIME.format(System.currentTimeMillis());
    }
    public static String getDateTime(String format){
        return new SimpleDateFormat(format).format(System.currentTimeMillis());
    }

    public static long subtractDate(Date dateStart, Date dateEnd) {
        return dateEnd.getTime() - dateStart.getTime();
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }

    /**
     * 获取本周的第几天的时间
     * @param day
     * @return
     */
    public static Date getDateOfWeek(int day){
        int dayOfWeek = getDayOfWeek();
        Date date;
        if (day == dayOfWeek){
            date = new Date();
        }else if(day < dayOfWeek){
            date = getFrontDay(new Date(),dayOfWeek - day);
        } else {
            date = getNextDay(new Date(),day - dayOfWeek);
        }
        return date;
    }
    public static String getDateValueOfWeek(int day){
       Date date = getDateOfWeek(day);
        return formatDate_YMD(date.getTime());
    }
    public static String getDateMDValueOfWeek(int day){
        Date date = getDateOfWeek(day);
        return formatDate_MD(date.getTime());
    }
    /**
     * 判断两天是否是同一天
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameDay(long day1, long day2){
        Date date1 = new Date(day1);
        Date date2 = new Date(day2);
        GregorianCalendar ca1 = new GregorianCalendar();
        GregorianCalendar ca2 = new GregorianCalendar();
        ca1.setTime(date1);
        ca2.setTime(date2);
        if(ca1.get(GregorianCalendar.YEAR) == ca2.get(GregorianCalendar.YEAR) &&
                ca1.get(GregorianCalendar.MONTH) == ca2.get(GregorianCalendar.MONTH) &&
                ca1.get(GregorianCalendar.DAY_OF_MONTH ) == ca2.get(GregorianCalendar.DAY_OF_MONTH)){
            return true;
        } else {
            return false;
        }
    }
    // 获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    // 获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    // 获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    // 获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    // 获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    // 获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取上个月的开始时间
    public static Date getBeginDayOfLastMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, -1);
        calendar.set(calendar.DAY_OF_MONTH, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取上个月的结束时间
    public static Date getEndDayOfLastMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, -1);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(calendar.DAY_OF_MONTH, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    // 获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    // 获取某个日期的开始时间
    public static Date getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Date getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Date(calendar.getTimeInMillis());
    }

    // 获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(Calendar.YEAR));
    }

    // 获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.MONTH) + 1;
    }

    // 两个日期相减得到的天数
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);

        int days = new Long(diff).intValue();

        return days;
    }

    // 两个日期相减得到的毫秒数
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    // 获取两个日期中的最大日期
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    // 获取两个日期中的最小日期
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    // 返回某月该季度的第一个月
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    /**
     * 获取某个日期下几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    /**
     * 获取某个日期前几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    /**
     * 获取某个日期前 多少年
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontYear(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - i);
        return cal.getTime();
    }

    /**
     * 获取某个日期后 多少年
     * @param date
     * @param i
     * @return
     */
    public static Date getNextYear(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + i);
        return cal.getTime();
    }
    // 获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }

                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    // 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    // 获取某年某月的第一天日期
    public static Date getStartMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    // 获取某年某月的最后一天日期
    public static Date getEndMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 計算时间差格式化
     * @param starTime
     * @param endTime
     * @return  返回时间差
     */
    public static String getDiffDay(long starTime,long endTime){
        String timeString = "";
        try {
            Date parse = new Date(starTime);
            Date parse1 = new Date(endTime);
            long diff = parse1.getTime() - parse.getTime();
            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
            long hour1 = diff / (60 * 60 * 1000);
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            timeString = hour1 + "小时" + min1 + "分";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeString;
    }
    /**
     * 将时间戳转换成描述性时间（昨天、今天、明天）
     *
     * @param timestamp 时间戳
     * @return 描述性日期
     */
    public static String descriptiveDate(long timestamp) {
        String descriptiveText = null;
        String format = "yyyy-MM-dd HH:mm:ss";
        //当前时间
        Calendar currentTime = Calendar.getInstance();
        //要转换的时间
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timestamp);
        //年相同
        if (currentTime.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
            //获取一年中的第几天并相减，取差值
            int diff = currentTime.get(Calendar.DAY_OF_YEAR) - time.get(Calendar.DAY_OF_YEAR);
            switch (diff) {
                case 1://当前比目标多一天，那么目标就是昨天
                    descriptiveText = "昨天";
                    format = "HH:mm:ss";
                    break;
                case 0://当前和目标是同一天，就是今天
                    descriptiveText = "今天";
                    format = "HH:mm:ss";
                    break;
                case -1://当前比目标少一天，就是明天
                    descriptiveText = "明天";
                    format = "HH:mm:ss";
                    break;
                default:
                    descriptiveText = null;
                    format = "yyyy-MM-dd HH:mm:ss";
                    break;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String formatDate = simpleDateFormat.format(time.getTime());
        if (!TextUtils.isEmpty(descriptiveText)) {
            return descriptiveText + " " + formatDate;
        }
        return formatDate;
    }
    /**
     * 将时间戳转换成描述性时间（昨天、今天、明天）
     *
     * @param timestamp 时间戳
     * @return 描述性日期
     */
    public static String descMessageDate(long timestamp) {
        String descriptiveText = null;
        String format = "yyyy-MM-dd HH:mm:ss";
        //当前时间
        Calendar currentTime = Calendar.getInstance();
        //要转换的时间
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timestamp);
        //年相同
        if (currentTime.get(Calendar.YEAR) == time.get(Calendar.YEAR)) {
            //获取一年中的第几天并相减，取差值
            int diff = currentTime.get(Calendar.DAY_OF_YEAR) - time.get(Calendar.DAY_OF_YEAR);
            int abs = Math.abs(diff);
            if (diff == 0){
                descriptiveText = "今天";
                format = "HH:mm";
            } else if (diff == 1){
                descriptiveText = "昨天";
                format = "HH:mm";
            } else if (abs == 2){
                descriptiveText = "最近三天时间";
                format = "";
                return descriptiveText;
            } else {
                descriptiveText = null;
                format = "yyyy-MM-dd HH:mm";
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String formatDate = simpleDateFormat.format(time.getTime());
        if (!TextUtils.isEmpty(descriptiveText)) {
            return descriptiveText + " " + formatDate;
        }
        return formatDate;
    }
}
