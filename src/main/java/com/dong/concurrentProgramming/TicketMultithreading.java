package com.dong.concurrentProgramming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 通过卖票程序读懂多线程--多线程程序
 *
 * @author LD
 * @date 2020/8/11 10:32
 */
public class TicketMultithreading extends Thread {

    private static final String ROOM = "中央放映厅";//放映厅
    private static final int ROW = 30;//座位
    private static final int COL = 20;//座位
    private static final String FILM_NAME = "战狼3";//电影名称
    private static final BigDecimal PRICE = BigDecimal.valueOf(30);//价格

    private static volatile List<CinemaTicket> ticketList = new ArrayList<>();//电影票数
    private static final int CUSTOMER_COUNT = 800;//顾客数
    private static volatile List<Customer> customerList = new ArrayList<>(CUSTOMER_COUNT);//顾客
    private static int customerId = 1;

    public TicketMultithreading(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (TicketMultithreading.class) {

            while (ticketList.size() > 0 && customerId <= CUSTOMER_COUNT) {
                CinemaTicket ticket = ticketList.get(ticketList.size() - 1);

                Customer customer = new Customer(customerId);
                customer.buyTicket(ticket);//顾客买票
                ticketList.remove(ticket);//卖出票后需要移除一张
                customerList.add(customer);
                System.out.println(Thread.currentThread().getName() + ":" + customerId + "号顾客买到了"
                        + "第" + customer.getTicket().getRow() + "行，第" + customer.getTicket().getCol() + "列的票");
                System.out.println("剩余票数：" + ticketList.size());
                customerId++;
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
        /*for (CinemaTicket cinemaTicket : ticketList) {
            System.out.println(cinemaTicket.toString());
        }*/

        //顾客到售票点进行随机买票
        Collections.shuffle(ticketList);//打乱票序

        TicketMultithreading threadA = new TicketMultithreading("售票点A");
        TicketMultithreading threadB = new TicketMultithreading("售票点B");
        TicketMultithreading threadC = new TicketMultithreading("售票点C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("电影票出售情况如下：");
        //剩余票的状态
        System.out.println("总共票数:" + ROW * COL + ",剩余票数：" + ticketList.size());
        for (CinemaTicket cinemaTicket : ticketList) {
            System.out.println(cinemaTicket.toString());
        }
        //顾客购买情况
        System.out.println("买到票的人数：" + customerList.size());
        /*for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }*/
        System.out.println("未买到票的人数：" + (CUSTOMER_COUNT - customerList.size()));

    }
}
