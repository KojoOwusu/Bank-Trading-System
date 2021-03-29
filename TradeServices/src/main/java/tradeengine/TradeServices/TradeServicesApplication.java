package tradeengine.TradeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import tradeengine.TradeServices.jedisconfig.OrdersPubSub;
import tradeengine.TradeServices.marketdata.MarketData;

import java.util.List;

@SpringBootApplication
public class TradeServicesApplication implements ApplicationRunner {
	@Autowired
	Jedis jedis;

	@Autowired
	MarketData MD;

	@Autowired
	OrdersPubSub ordersPubSub;

	public static void main(String[] args) {
		SpringApplication.run(TradeServicesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		MD.init();
		jedis.subscribe(ordersPubSub, "Channel#tradeengine");
	}
}

