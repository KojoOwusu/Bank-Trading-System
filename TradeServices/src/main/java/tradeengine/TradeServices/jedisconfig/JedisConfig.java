package tradeengine.TradeServices.jedisconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

    @Bean
    public static Jedis jedis(){
        Jedis jedis = new Jedis("redis-14349.c81.us-east-1-2.ec2.cloud.redislabs.com", 14349);
        jedis.auth("fcTHon925fcjUDen1ujM4x5Ra1PsJYIk");
        return jedis;
    }

}

