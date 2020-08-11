package com.dong.concurrentProgramming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 通过卖票程序读懂多线程--单线程程序
 *
 * @author LD
 * @date 2020/8/11 9:46
 */
public class TicketSingle {
    private static final String ROOM = "中央放映厅";//放映厅
    private static final int ROW = 10;//座位
    private static final int COL = 20;//座位
    private static final String FILM_NAME = "战狼3";//电影名称
    private static final BigDecimal PRICE = BigDecimal.valueOf(30);//价格

    private static List<CinemaTicket> ticketList = new ArrayList<>();//电影票数
    private static final int CUSTOMER_COUNT = 250;//顾客数
    private static List<Customer> customerList = new ArrayList<>(CUSTOMER_COUNT);//顾客


    public static void main(String[] args) {
        //中央放映厅总共有250个座位，2020-05-12 18:00 放映战狼3，售票价格为30元
        int ticketId = 1;
        for (int row = 1; row <= ROW; row++) {
            for (int col = 1; col <= COL; col++) {
                CinemaTicket ticket = new CinemaTicket(ticketId++, ROOM, row, col, FILM_NAME, PRICE,
                        LocalDateTime.of(2020, 5, 10, 18, 0));
                ticketList.add(ticket);
            }
        }

        //输出电影票信息
        for (CinemaTicket cinemaTicket : ticketList) {
            System.out.println(cinemaTicket.toString());
        }

        //顾客到售票点进行随机买票
        Collections.shuffle(ticketList);//打乱票序
        int index = 1;
        while (ticketList.size() > 0 && index <= CUSTOMER_COUNT) {
            CinemaTicket ticket = ticketList.get(ticketList.size() - 1);
            Customer customer = new Customer(index);
            customer.buyTicket(ticket);//顾客买票
            ticketList.remove(ticket);//卖出票后需要移除一张
            customerList.add(customer);
            System.out.println(index + "号顾客买到了"
                    + "第" + customer.getTicket().getRow() + "行，第" + customer.getTicket().getCol() + "列的票");
            System.out.println("剩余票数：" + ticketList.size());
            index++;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("电影票出售情况如下：");
        //剩余票的状态
        System.out.println("剩余票数：" + ticketList.size());
        for (CinemaTicket cinemaTicket : ticketList) {
            System.out.println(cinemaTicket.toString());
        }
        //顾客购买情况
        System.out.println("买到票的人数：" + customerList.size());
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        System.out.println("未买到票的人数：" +(CUSTOMER_COUNT- customerList.size()));
    }

}
