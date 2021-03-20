package tradeengine.TradeServices;

public class Response {
    private String orderID;
    private String exchange;

    public Response(){};
    public Response(String orderID, String exchange) {
        this.orderID = orderID;
        this.exchange = exchange;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getExchange() {
        return exchange;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}

