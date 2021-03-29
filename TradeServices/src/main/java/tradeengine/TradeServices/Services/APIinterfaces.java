package tradeengine.TradeServices.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import tradeengine.TradeServices.OrderStatus;
import tradeengine.TradeServices.TradeData;

import java.util.List;

public class APIinterfaces {
        public static interface GetMarketData {
                @GET("/md")
                Call<List<TradeData>> getMarketData();
        }

        public static interface GetOrderBookData {
                @GET("/api/orderbook")
                Call<List<OrderStatus>> getOrderBookData(@Query("product") String product, @Query("side") String side, @Query("exchange") String exchange);
        }
}
