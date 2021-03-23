package tradeengine.TradeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tradeengine.TradeServices.Services.GetMarketData;
import tradeengine.TradeServices.jedisconfig.OrdersPubSub;
import tradeengine.TradeServices.marketdata.MarketData;

import java.util.List;

@SpringBootApplication
public class TradeServicesApplication implements ApplicationRunner {
	@Autowired
	Jedis jedis;

	@Autowired
	MarketData MD;

	private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();
	private Retrofit retrofit2 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();

	public static void main(String[] args) {
		SpringApplication.run(TradeServicesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var marketdata1Service = retrofit.create(GetMarketData.class);
		var marketdata2Service = retrofit2.create(GetMarketData.class);

		Call<List<TradeData>> response = marketdata1Service.getMarketData();
		Call<List<TradeData>> response2 = marketdata2Service.getMarketData();

		MD.setData1(response.execute().body());
		MD.setData2(response2.execute().body());

		jedis.subscribe(new OrdersPubSub(), "Channel#tradeengine");
	}
}

