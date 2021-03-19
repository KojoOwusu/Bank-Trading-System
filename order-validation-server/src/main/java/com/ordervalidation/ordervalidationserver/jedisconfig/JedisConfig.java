package com.ordervalidation.ordervalidationserver.jedisconfig;


import redis.clients.jedis.Jedis;

public class JedisConfig {
    public static Jedis createJedisClient(){
        Jedis jedisclient = new Jedis("redis-14349.c81.us-east-1-2.ec2.cloud.redislabs.com", 14349);
        jedisclient.auth("fcTHon925fcjUDen1ujM4x5Ra1PsJYIk");
        return jedisclient;
    }
}
