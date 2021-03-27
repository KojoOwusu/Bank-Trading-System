package com.ordervalidation.ordervalidationserver.marketdata;

import java.util.List;

public class MarketData {
    private List<Trade> data;
    private List<Trade> data2;

    public MarketData(List<Trade> data, List<Trade> data2)  {
        this.data = data;
        this.data2 = data2;
    }

    public MarketData(){ }

    public List<Trade> getData() {
        return data;
    }

    public List<Trade> getData2() {
        return data2;
    }

    public void setData(List<Trade> data) {
        this.data = data;
    }

    public void setData2(List<Trade> data2) {
        this.data2 = data2;
    }
}
