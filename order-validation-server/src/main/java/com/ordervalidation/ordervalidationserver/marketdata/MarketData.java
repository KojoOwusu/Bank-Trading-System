package com.ordervalidation.ordervalidationserver.marketdata;

import java.util.List;

public class MarketData {
    private List<Trade> data;

    public MarketData(List<Trade> data) {
        this.data = data;
    }

    public MarketData(){ }

    public List<Trade> getData() {
        return data;
    }

    public void setData(List<Trade> data) {
        this.data = data;
    }
}
