package com.dong.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 当前日期工具类
 *
 * 当前日
 *
 * 当前星期
 *
 * 当前周数
 *
 * 当前月份
 *
 * 当前季度
 *
 * 当前年份
 */
public class CurrentDateUtils {

    /**
     * 获取 当前年、半年、季度、月、日、小时 开始结束时间
     */
    private final SimpleDateFormat sdfDate;
    private final SimpleDateFormat sdfDateTime;

    private CurrentDateUtils() {
        this.sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        this.sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    //当前日期
    private String getCurrentDate(){
        return sdfDate.format(new Date());
    }

    //当前时间
    private String getCurrentDateTime(){
        return sdfDateTime.format(new Date());
    }

    //当前日
    private int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 当前星期
     * @param type 周/星期
     * @return
     */
    private String getCurrentWeek(String type) {
        String result = "";
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (week == 1){
            result = "一";
        }else if (week == 2){
            result = "二";
        }else if (week == 3){
            result = "三";
        }else if (week == 4){
            result = "四";
        }else if (week == 5){
            result = "五";
        }else if (week == 6){
            result = "六";
        }else if (week == 7){
            if ("星期".equals(type)) {
                result = "天";
            }else {
                result = "日";
            }
        }
        result = type + result;
        return result;
    }

    //当前周(月)
    private int getCurrentWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    //当前周(年)
    private int getCurrentWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    //当前月份
    private int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    //当前季度
    private int getCurrentQuarter() {
        int quarter = 0;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        /* 一旦其中一个 else if 语句检测为 true，其他的 else if 以及 else 语句都将跳过执行。*/
        /*if (month <= 3){
            quarter = 1;
        }else if (month >= 4 && month <=6){
            quarter = 2;
        }else if (month >= 7 && month <=9){
            quarter = 3;
        }else if (month >= 10 && month <=12){
            quarter = 4;
        }*/
        if (month <= 3){
            quarter = 1;
        }else if (month <= 6){
            quarter = 2;
        }else if (month <= 9){
            quarter = 3;
        }else if (month <= 12){
            quarter = 4;
        }
        return quarter;
    }

    //当前年份
    private int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(sdfDateTime.parse(sdfDate.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }


    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(sdfDateTime.parse(sdfDate.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = sdfDate.parse(sdfDate.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     */
    public Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = sdfDateTime.parse(sdfDate.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = sdfDate.parse(sdfDate.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = sdfDateTime.parse(sdfDate.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 4);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            now = sdfDateTime.parse(sdfDate.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = sdfDateTime.parse(sdfDate.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

    public static void main(String[] args) {
        /*CurrentDateUtils currentDateUtils = new CurrentDateUtils();

        String date = currentDateUtils.getCurrentDate();
        System.out.println(date);

        String datetime = currentDateUtils.getCurrentDateTime();
        System.out.println(datetime);

        int day = currentDateUtils.getCurrentDay();
        System.out.println(day);

        String weekStr = currentDateUtils.getCurrentWeek("星期");
        System.out.println(weekStr);

        int week = currentDateUtils.getCurrentWeekOfMonth();
        System.out.println(week);

        int month = currentDateUtils.getCurrentMonth();
        System.out.println(month);

        int quarter = currentDateUtils.getCurrentQuarter();
        System.out.println(quarter);

        int year = currentDateUtils.getCurrentYear();
        System.out.println(year);*/

        for (int i=0;i<999;i++){
            Date date = randomDate("2020-02-01","2020-02-28");
            System.out.println(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));
        }
    }
}
