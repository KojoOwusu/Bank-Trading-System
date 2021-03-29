package com.ordervalidation.ordervalidationserver;

import com.ordervalidation.ordervalidationserver.marketdata.MarketData;
import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@SpringBootApplication
public class OrderValidationServerApplication implements ApplicationRunner {
	@Autowired
	MarketData MD;

	private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();
	private Retrofit retrofit2 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange2.matraining.com").build();

	public static void main(String[] args) {
		SpringApplication.run(OrderValidationServerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {
		var marketdataService = retrofit.create(GetMarketData.class);
		var marketdata2Service = retrofit.create(GetMarketData.class);

		try {
			Call<List<Trade>> response = marketdataService.getMarketData();
			MD.setData(response.execute().body());
			MD.setData2(marketdata2Service.getMarketData().execute().body());
		}catch(java.io.IOException e){e.printStackTrace();}
	}
}
