package com.tradingsystem.reportingservice;

public class Execution {
    private String timestamp;
    private  double price;
    private int quantity;

    public Execution(){}

    public Execution(String timestamp, double price, int quantity) {
        this.timestamp = timestamp;
        this.price = price;
        this.quantity = quantity;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
