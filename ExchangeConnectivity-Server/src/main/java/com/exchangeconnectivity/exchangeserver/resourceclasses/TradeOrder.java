package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class TradeOrder extends Order{
    private int portfolioID;
    private double funds;
    private int quantityOwned;
    private String exchange;

    public TradeOrder(){
        super();
    }

    public TradeOrder(String product, int quantity, double price, String side, int portfolioID, double funds, int quantityOwned, String exchange) {
        super(product, quantity, price, side);
        this.portfolioID = portfolioID;
        this.funds = funds;
        this.quantityOwned = quantityOwned;
        this.exchange = exchange;
    }

    public double getFunds() {
        return funds;
    }

    public int getQuantityOwned() {
        return quantityOwned;
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

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void setQuantityOwned(int quantityOwned) {
        this.quantityOwned = quantityOwned;
    }
}
