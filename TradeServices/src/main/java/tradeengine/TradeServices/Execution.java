package tradeengine.TradeServices;

public class Execution {
    private String timestamp;
    private double price;
    private int quantity;

    public Execution(String timestamp, double price, int quantity) {
        this.timestamp = timestamp;
        this.price = price;
        this.quantity = quantity;
    }

    public Execution(){}

    public String getTimestamp() {
        return timestamp;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
