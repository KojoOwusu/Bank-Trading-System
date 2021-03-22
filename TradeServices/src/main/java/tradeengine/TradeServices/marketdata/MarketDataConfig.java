package tradeengine.TradeServices.marketdata;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketDataConfig {
     @Bean
    public MarketData marketdata(){
         return new MarketData();
    }
}
