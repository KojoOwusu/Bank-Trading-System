package tradeengine.TradeServices.jedisconfig;


import com.thoughtworks.qdox.model.expression.Or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tradeengine.TradeServices.Services.TradeengineService;

@Configuration
public class JedisConfig {

    @Bean
    public static Jedis jedis(){
        Jedis jedis = new Jedis("redis-14349.c81.us-east-1-2.ec2.cloud.redislabs.com", 14349);
        jedis.auth("fcTHon925fcjUDen1ujM4x5Ra1PsJYIk");
        return jedis;
        //"redis-14349.c81.us-east-1-2.ec2.cloud.redislabs.com", 14349
    }

    @Bean
    public static OrdersPubSub ordersPubSub(){
        return new OrdersPubSub();
    }

}

