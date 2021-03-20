package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class Order {
    private  String product;
    private  int quantity;
    private  double price;
    private String side;


    public Order(String product, int quantity, double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
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
}
