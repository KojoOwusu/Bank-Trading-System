package com.ordervalidation.ordervalidationserver;

import com.ordervalidation.ordervalidationserver.marketdata.MarketData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketDataConfig {

    @Bean
    public MarketData getMarketData() {
        return new MarketData();
    }

}

