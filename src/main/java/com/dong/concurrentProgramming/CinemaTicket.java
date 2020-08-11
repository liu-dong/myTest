package com.dong.concurrentProgramming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 电影票类
 *
 * @author LD
 * @date 2020/8/11 9:42
 */
public class CinemaTicket {

    //id
    private int ticketId;
    //放映厅
    private String room;
    //行
    private Integer row;
    //列
    private Integer col;
    //电影名
    private String filmName;
    //价格
    private BigDecimal price;
    //放映时间
    private LocalDateTime datetime;
    //窗口
    private String window;

    public CinemaTicket(int ticketId, String room, Integer row, Integer col, String filmName, BigDecimal price, LocalDateTime datetime) {
        this.ticketId = ticketId;
        this.room = room;
        this.row = row;
        this.col = col;
        this.filmName = filmName;
        this.price = price;
        this.datetime = datetime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    @Override
    public String toString() {
        return "CinemaTicket{" +
                "ticketId=" + ticketId +
                ", room='" + room + '\'' +
                ", row=" + row +
                ", col=" + col +
                ", filmName='" + filmName + '\'' +
                ", price=" + price +
                ", datetime=" + datetime +
                '}';
    }
}
