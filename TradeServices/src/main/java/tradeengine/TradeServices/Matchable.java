package tradeengine.TradeServices;

import java.util.List;

public class Matchable extends OrderStatus implements Comparable<Matchable>{
    private String exchange;

    public Matchable(String product, int quantity, double price, String side, List<Execution> executions, int cumulativeQuantity, String exchange) {
        super(product, quantity, price, side, executions, cumulativeQuantity);
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }


    @Override
    public int compareTo(Matchable o) {
        return Double.compare(this.getPrice(),o.getPrice());
    }
}
