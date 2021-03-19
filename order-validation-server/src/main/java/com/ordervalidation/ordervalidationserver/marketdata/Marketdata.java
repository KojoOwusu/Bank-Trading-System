package com.ordervalidation.ordervalidationserver.marketdata;

import java.util.List;

public class Marketdata {
    private final List<Trade> marketdata;

    public Marketdata(List<Trade> marketdata) {
        this.marketdata = marketdata;
    }

    public List<Trade> getMarketdata() {
        return marketdata;
    }
}
