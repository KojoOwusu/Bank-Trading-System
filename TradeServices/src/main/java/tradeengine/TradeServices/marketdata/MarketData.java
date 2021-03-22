package tradeengine.TradeServices.marketdata;

import tradeengine.TradeServices.TradeData;

import java.util.List;

public class MarketData {
    private List<TradeData> data;

    public MarketData(List<TradeData> data) {
        this.data = data;
    }

    public MarketData(){ }

    public List<TradeData> getData() {
        return data;
    }

    public void setData(List<TradeData> data) {
        this.data = data;
    }
}
