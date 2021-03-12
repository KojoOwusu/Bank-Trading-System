package com.exchangeconnectivity.exchangeserver.resourceclasses;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private final String product;
    private final int quantity;
    private final double price;
    private final String side;
    private final List<Execution> executions;
    private final int cumulativeQuantity;

    public OrderResponse(String product, int quantity, double price, String side, List<Execution> executions, int cumulativeQuantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.executions=executions;
        this.cumulativeQuantity=cumulativeQuantity;
    }
    public OrderResponse(){

        this.product = "";
        this.quantity = 0;
        this.price = 0.0;
        this.side = "";
        this.executions= new ArrayList<Execution>();
        this.cumulativeQuantity=0;
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
}
