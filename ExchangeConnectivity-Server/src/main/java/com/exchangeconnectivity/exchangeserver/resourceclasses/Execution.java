package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class Execution {
    private final String timestamp;
    private final double price;
    private final int quantity;

    public Execution(String timestamp, double price, int quantity){
        this.timestamp = timestamp;
        this.price=price;
        this.quantity=quantity;
    }
    public Execution(){
        this.timestamp="";
        this.price=0.0;
        this.quantity=0;
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
