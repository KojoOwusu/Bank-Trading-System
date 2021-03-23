package tradeengine.TradeServices.marketdata;

import tradeengine.TradeServices.TradeData;

import java.util.List;

public class MarketData {
    private List<TradeData> data1;
    private List<TradeData> data2;

    public MarketData(List<TradeData> data1, List<TradeData> data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
    public MarketData(List<TradeData> data1){
        this.data1=data1;
    }
    public MarketData(){ }

    public List<TradeData> getData1() {
        return data1;
    }

    public List<TradeData> getData2() {
        return data2;
    }

    public void setData1(List<TradeData> data1) {
        this.data1 = data1;
    }

    public void setData2(List<TradeData> data2) {
        this.data2 = data2;
    }
}
