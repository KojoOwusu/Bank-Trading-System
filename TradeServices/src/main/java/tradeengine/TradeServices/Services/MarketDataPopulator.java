package tradeengine.TradeServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tradeengine.TradeServices.TradeData;

import java.io.IOException;
import java.util.List;

public class MarketDataPopulator {
    private APIinterfaces.GetMarketData marketDataServiceInterface;

   public MarketDataPopulator(String BASE_URL){
       marketDataServiceInterface = ExchangeServices.getMarketDataInterface(BASE_URL);
   }
   public List<TradeData> fetchMarketData() throws IOException {
        Call<List<TradeData>> response =marketDataServiceInterface.getMarketData();
        return response.execute().body();
   }
}

