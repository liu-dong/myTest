package com.dong.javaBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 三种代码方式的执行顺序
 */
public class HelloA {

//    int i = 0;
//
//    public HelloA() {
//        System.out.println("I'm A construct method code block:"+(i++));
//    }
//
//    {
//        System.out.println("I'm A construct code block"+(i++));
//    }
//
//    static {
//        System.out.println("I'm A static code block");
//    }

    public static void main(String[] args) throws ParseException {
        HelloA helloA = new HelloA();
        String str = helloA.getLastYearDate("2019-08");
        System.out.println(str);
    }

    //字符串日期减一年
    public String getLastYearDate(String dateStr) throws ParseException {
        String lastYearDate = "";
        SimpleDateFormat sdfM = new SimpleDateFormat("yyyy-MM");
        Date date = sdfM.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.YEAR, -1);//减一年
        date = cal.getTime();
        lastYearDate = sdfM.format(date);
        return lastYearDate;
    }
}
