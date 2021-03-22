package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class TradeOrder extends Order{
    private String exchange;
    private int portfolioID;

    public TradeOrder(){
        super();
    }

    public TradeOrder(String product, int quantity, double price, String side, String exchange, int portfolioID) {
        super(product, quantity, price, side);
        this.exchange = exchange;
        this.portfolioID = portfolioID;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public int getPortfolioID() {
        return portfolioID;
    }
}
