package tradeengine.TradeServices;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostOrderService {
    @POST("api/createorder")
    Call<String> createOrder(@Body Order order);
}
