package com.dong.javaThink.tenth;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author liudong 2022/8/25
 */
public class Test {
    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        Parcel1.Contents contents = p.new Contents();

        Date date1 = DateUtil.parseDate("2022-08-10");
        Date date2 = DateUtil.parseDate("2022-08-11");
        boolean before = date2.before(date1);
        System.out.println(before);
    }
}
