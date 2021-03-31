package tradeengine.TradeServices.marketdata;

import tradeengine.TradeServices.Services.MarketDataPopulator;
import tradeengine.TradeServices.TradeData;

import java.util.List;

public class MarketData {
    private List<TradeData> data1;
    private List<TradeData> data2;

    public MarketData(){}

    public void init(){
        MarketDataPopulator marketDataPopulator = new MarketDataPopulator("https://exchange.matraining.com");
        MarketDataPopulator marketDataPopulator2 = new MarketDataPopulator("https://exchange2.matraining.com");
        try {
            this.data1 = marketDataPopulator.fetchMarketData();
            this.data2 = marketDataPopulator2.fetchMarketData();
        }catch (java.io.IOException e){e.printStackTrace();
       }
    }

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
