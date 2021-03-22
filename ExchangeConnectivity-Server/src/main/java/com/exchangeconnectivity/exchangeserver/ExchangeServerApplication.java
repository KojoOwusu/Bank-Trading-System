package com.exchangeconnectivity.exchangeserver;

import com.exchangeconnectivity.exchangeserver.jedisconfig.OrdersPubSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@RestController
public class ExchangeServerApplication implements ApplicationRunner {
	@Autowired
	PostOrderService postOrderService;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeServerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		postOrderService.order();
	}
}

