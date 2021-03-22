package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class OpenOrder extends TradeOrder{
    private String orderStatus;
    private String orderID;


    public OpenOrder(String product, int quantity, double price, String side, String exchange, int portfolioID, String orderStatus, String orderID) {
        super(product, quantity, price, side, exchange, portfolioID);
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
}
