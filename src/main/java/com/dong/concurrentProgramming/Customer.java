package com.dong.concurrentProgramming;


/**
 * 顾客类
 *
 * @author LD
 * @date 2020/8/11 9:44
 */
public class Customer {

    //顾客id
    private int customerId;
    //购买的电影票
    private CinemaTicket cinemaTicket;

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    //顾客买票
    public void buyTicket(CinemaTicket cinemaTicket) {
        this.cinemaTicket = cinemaTicket;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CinemaTicket getTicket() {
        return cinemaTicket;
    }

    public void setTicket(CinemaTicket cinemaTicket) {
        this.cinemaTicket = cinemaTicket;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", cinemaTicket=" + cinemaTicket.toString() +
                '}';
    }
}
