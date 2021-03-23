package com.tradingsystem.reportingservice;

import com.tradingsystem.reportingservice.jedisconfig.OrdersPubSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class ReportingServiceApplication implements ApplicationRunner {
	@Autowired
	OrdersPubSub ordersPubSub;

	@Value("${run.with.runner}")
	String env;

	@Autowired
	Jedis jedis;

	public static void main(String[] args) {
		SpringApplication.run(ReportingServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(env.equals("true")){
			//get update from all microservices
			jedis.subscribe(ordersPubSub,"Channel#completed","Channel#client","Channel#ordervalidation","Channel#processing");
		}
	}
}
