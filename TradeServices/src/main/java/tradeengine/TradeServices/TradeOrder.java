package tradeengine.TradeServices;

public class TradeOrder extends Order{
    private String exchange;

    public TradeOrder(){};

    public TradeOrder(String product, int quantity, double price, String side, int portfolioID, double funds, int quantityOwned, String exchange) {
        super(product, quantity, price, side, portfolioID, funds, quantityOwned);
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}

