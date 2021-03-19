package com.ordervalidation.ordervalidationserver.jedisconfig;


import redis.clients.jedis.Jedis;

public class JedisConfig {
    public static Jedis createJedisClient(){
        return new Jedis();
    }
}
