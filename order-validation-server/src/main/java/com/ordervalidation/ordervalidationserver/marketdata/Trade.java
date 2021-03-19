package com.ordervalidation.ordervalidationserver.marketdata;

public class Trade {
    private final String TICKER;
    private final int SELL_LIMIT;
    private final double LAST_TRADED_PRICE;
    private final double MAX_PRICE_SHIFT;
    private final double ASK_PRICE;
    private final double BID_PRICE;
    private final int BUY_LIMIT;

    public Trade(String TICKER, int SELL_LIMIT, double LAST_TRADED_PRICE, double MAX_PRICE_SHIFT, double ASK_PRICE, double BID_PRICE, int BUY_LIMIT) {
        this.TICKER = TICKER;
        this.SELL_LIMIT = SELL_LIMIT;
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
        this.ASK_PRICE = ASK_PRICE;
        this.BID_PRICE = BID_PRICE;
        this.BUY_LIMIT = BUY_LIMIT;
    }

    public String getTICKER() {
        return TICKER;
    }

    public int getSELL_LIMIT() {
        return SELL_LIMIT;
    }

    public double getLAST_TRADED_PRICE() {
        return LAST_TRADED_PRICE;
    }

    public double getMAX_PRICE_SHIFT() {
        return MAX_PRICE_SHIFT;
    }

    public double getASK_PRICE() {
        return ASK_PRICE;
    }

    public double getBID_PRICE() {
        return BID_PRICE;
    }

    public int getBUY_LIMIT() {
        return BUY_LIMIT;
    }
}
