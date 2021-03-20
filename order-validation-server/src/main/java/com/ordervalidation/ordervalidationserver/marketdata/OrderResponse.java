package com.ordervalidation.ordervalidationserver.marketdata;

public class OrderResponse {
    private String orderID;
    private String exchange;

    public OrderResponse(){}
    public OrderResponse(String orderID, String exchange) {
        this.orderID = orderID;
        this.exchange = exchange;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getExchange() {
        return exchange;
    }
}
