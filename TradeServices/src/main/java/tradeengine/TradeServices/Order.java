package tradeengine.TradeServices;

import java.math.BigDecimal;

public class Order {
        private String product;
        private int quantity;
        private double price;
        private String side;
        private int portfolioID;
        private double funds;
        private int quantityOwned;

        public Order(){};

    public Order(String product, int quantity, double price, String side, int portfolioID, double funds, int quantityOwned) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.portfolioID = portfolioID;
        this.funds = funds;
        this.quantityOwned = quantityOwned;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", portfolioID=" + portfolioID +
                ", funds=" + funds +
                ", quantityOwned=" + quantityOwned +
                '}';
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

    public int getPortfolioID() {
        return portfolioID;
    }

    public double getFunds() {
        return funds;
    }

    public int getQuantityOwned() {
        return quantityOwned;
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

