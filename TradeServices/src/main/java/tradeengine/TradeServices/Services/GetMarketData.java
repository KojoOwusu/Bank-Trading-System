package tradeengine.TradeServices.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tradeengine.TradeServices.TradeData;

import java.util.List;

public interface GetMarketData {
        @GET("/md")
        Call<List<TradeData>> getMarketData();
}
