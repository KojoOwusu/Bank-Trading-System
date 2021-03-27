package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class OpenOrder {
    private String product;
    private int quantity;
    private double price;
    private String side;
    private String exchange;
    private int portfolioID;
    private String orderStatus;
    private String orderID;
    private int executions;


    public OpenOrder(String product, int quantity, double price, String side, String exchange, int portfolioID, String orderStatus, String orderID, int executions) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchange = exchange;
        this.portfolioID = portfolioID;
        this.orderStatus = orderStatus;
        this.orderID = orderID;
        this.executions = executions;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setExecutions(int executions) {
        this.executions = executions;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getExecutions() {
        return executions;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    public String getExchange() {
        return exchange;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderID() {
        return orderID;
    }
}