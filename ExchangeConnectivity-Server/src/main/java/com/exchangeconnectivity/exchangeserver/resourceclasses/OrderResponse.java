package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class OrderResponse {
    private String orderID;
    private String exchange;

    public OrderResponse(String orderID, String exchange) {
        this.orderID = orderID;
        this.exchange = exchange;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getExchange() {
        return exchange;
    }
}
