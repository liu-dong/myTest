package com.dong.javaBase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LD
 * @date 2020/8/17 14:39
 */
public class BreakAndContinueTest {


    public static void main(String[] args) {

//        String url = "//i.autohome.com.cn/75975500";
//        String user_id = url.replaceAll("\\D", "");
//        System.out.println(user_id);

        /*String str = "1123";
        Pattern pattern = Pattern.compile("[0-9]*");
        if (pattern.matcher(str).matches()){
            System.out.println("是数字");
        }*/

        /*for (int i = 0; i <= 5; i++) {
         *//* for (int j = 1; j <= 3; j++) {
                if (j > 2) {
                    if (j == 3){
                        continue;
                    }
                    continue;
                }
                System.out.println("输出结果：" + ("b" + j));
            }*//*
            if (i >= 3 && i<5) {
//                if (i == 4){
//                    continue;
//                }
                System.out.println(i);
                continue;
            }
            System.out.println("输出结果：" + ("a" + i));
            System.out.println();
        }*/
        dateFormatter();
    }

    public static void dateFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,20);
        Date time = calendar.getTime();
        System.out.println(sdf.format(time));

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(20);
        System.out.println(localDateTime.format(FORMATTER));
    }

}
