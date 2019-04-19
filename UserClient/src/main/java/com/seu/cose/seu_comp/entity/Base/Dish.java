package com.seu.cose.seu_comp.entity.Base;

public class Dish {

    private int id;

    private String name;

    private int price;

    private int bookedTimesMonthly;

    private int bookedTimesWeekly;

    private String window;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBookedTimesMonthly() {
        return bookedTimesMonthly;
    }

    public void setBookedTimesMonthly(int bookedTimesMonthly) {
        this.bookedTimesMonthly = bookedTimesMonthly;
    }

    public int getBookedTimesWeekly() {
        return bookedTimesWeekly;
    }

    public void setBookedTimesWeekly(int bookedTimesWeekly) {
        this.bookedTimesWeekly = bookedTimesWeekly;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }
}
