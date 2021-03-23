package tradeengine.TradeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import tradeengine.TradeServices.jedisconfig.OrdersPubSub;

@SpringBootApplication
public class TradeServicesApplication implements ApplicationRunner {
	@Autowired
	Jedis jedis;

	@Value("${run.with.runner}")
	String env;

	public static void main(String[] args) {
		SpringApplication.run(TradeServicesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(env.equals("true")){
			jedis.subscribe(new OrdersPubSub(), "Channel#tradeengine");
		}
	}
}
