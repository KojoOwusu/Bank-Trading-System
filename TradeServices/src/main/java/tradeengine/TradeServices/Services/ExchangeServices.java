package tradeengine.TradeServices.Services;

import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ExchangeServices {

    private static Retrofit retrofitFactory (String BASE_URL){
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
    }

    public static APIinterfaces.GetMarketData getMarketDataInterface(String BASE_URL){
        Retrofit retrofit = retrofitFactory(BASE_URL);
        return retrofit.create(APIinterfaces.GetMarketData.class);
    }

    public static APIinterfaces.GetOrderBookData getOrderBookDataInterface(String BASE_URL){
        return retrofitFactory(BASE_URL).create(APIinterfaces.GetOrderBookData.class);
    }
}
