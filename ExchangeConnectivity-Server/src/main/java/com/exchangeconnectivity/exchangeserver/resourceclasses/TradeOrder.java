package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class TradeOrder extends Order{
    private String exchange;

    public TradeOrder(String product, int quantity, double price, String side, String exchange) {
        super(product, quantity, price, side);
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
