package tradeengine.TradeServices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeData {
    private java.lang.String TICKER;
    private int SELL_LIMIT;
    private double LAST_TRADED_PRICE;
    private double MAX_PRICE_SHIFT;
    private double ASK_PRICE;
    private double BID_PRICE;
    private int BUY_LIMIT;

    public TradeData(){ super(); }

    public TradeData(String TICKER, int SELL_LIMIT, double LAST_TRADED_PRICE, double MAX_PRICE_SHIFT, double ASK_PRICE, double BID_PRICE, int BUY_LIMIT) {
        this.TICKER = TICKER;
        this.SELL_LIMIT = SELL_LIMIT;
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
        this.ASK_PRICE = ASK_PRICE;
        this.BID_PRICE = BID_PRICE;
        this.BUY_LIMIT = BUY_LIMIT;
    }
    @JsonProperty("TICKER")
    public java.lang.String getTICKER() { return TICKER; }
    @JsonProperty("SELL_LIMIT")
    public int getSELL_LIMIT() {
        return SELL_LIMIT;
    }
    @JsonProperty("LAST_TRADED_PRICE")
    public double getLAST_TRADED_PRICE() {
        return LAST_TRADED_PRICE;
    }
    @JsonProperty("MAX_PRICE_SHIFT")
    public double getMAX_PRICE_SHIFT() {
        return MAX_PRICE_SHIFT;
    }
    @JsonProperty("ASK_PRICE")
    public double getASK_PRICE() {
        return ASK_PRICE;
    }
    @JsonProperty("BID_PRICE")
    public double getBID_PRICE() {
        return BID_PRICE;
    }
    @JsonProperty("BUY_LIMIT")
    public int getBUY_LIMIT() {
        return BUY_LIMIT;
    }


    public void setTICKER(String TICKER) {
        this.TICKER = TICKER;
    }

    public void setSELL_LIMIT(int SELL_LIMIT) {
        this.SELL_LIMIT = SELL_LIMIT;
    }

    public void setLAST_TRADED_PRICE(double LAST_TRADED_PRICE) {
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
    }

    public void setMAX_PRICE_SHIFT(double MAX_PRICE_SHIFT) {
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
    }

    public void setASK_PRICE(double ASK_PRICE) {
        this.ASK_PRICE = ASK_PRICE;
    }

    public void setBID_PRICE(double BID_PRICE) {
        this.BID_PRICE = BID_PRICE;
    }

    public void setBUY_LIMIT(int BUY_LIMIT) {
        this.BUY_LIMIT = BUY_LIMIT;
    }
}
