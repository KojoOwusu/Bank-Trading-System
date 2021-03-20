package tradeengine.TradeServices;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {
    @POST("api/createorder")
    Call<Response> createOrder(@Body TradeOrder order);
}
