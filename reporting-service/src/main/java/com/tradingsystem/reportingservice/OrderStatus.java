package com.tradingsystem.reportingservice;

import java.util.List;

public class OrderStatus {
    private  String product;
    private  int quantity;
    private  double price;
    private String side;
    private  List<Execution> executions;
    private  int cumulativeQuantity;

    public OrderStatus() { }

    public OrderStatus(String product, int quantity, double price, String side, List<Execution> executions, int cumulativeQuantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.executions = executions;
        this.cumulativeQuantity = cumulativeQuantity;
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

    public List<Execution> getExecutions() {
        return executions;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
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

    public void setExecutions(List<Execution> executions) {
        this.executions = executions;
    }

    public void setCumulativeQuantity(int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", executions=" + executions +
                ", cumulativeQuantity=" + cumulativeQuantity +
                '}';
    }
}


