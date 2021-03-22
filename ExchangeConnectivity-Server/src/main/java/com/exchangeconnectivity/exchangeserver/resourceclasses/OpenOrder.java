package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class OpenOrder{
    private  String product;
    private  int quantity;
    private  double price;
    private String side;
    private String exchange;
    private int portfolioID;
    private String orderStatus;
    private String orderID;


    public OpenOrder(String product, int quantity, double price, String side, String exchange, int portfolioID, String orderStatus, String orderID) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchange = exchange;
        this.portfolioID = portfolioID;
        this.orderStatus = orderStatus;
        this.orderID = orderID;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getProduct() {
        return product;
    }
}
