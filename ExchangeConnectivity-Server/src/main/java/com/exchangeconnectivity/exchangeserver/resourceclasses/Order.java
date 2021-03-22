package com.exchangeconnectivity.exchangeserver.resourceclasses;

public class Order {
    private  String product;
    private  int quantity;
    private  double price;
    private String side;

    public Order(){};
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
}
