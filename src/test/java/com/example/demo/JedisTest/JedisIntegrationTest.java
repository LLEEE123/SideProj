package com.example.demo.JedisTest;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JedisIntegrationTest {

    @Test
    void shouldStoreAndRetrieveValueFromRedis() {
        String redisIp = System.getenv("REDIS_IP");
        try
                (
//                Jedis jedis = new Jedis("redis", 6379) == null ?
//                        new Jedis("localhost", 6379) : new Jedis("redis", 6379)
                        Jedis jedis = new Jedis(redisIp, 6379)
//                Jedis jedis = new Jedis("redis", 6379)
//                Jedis jedis = new Jedis("localhost", 6379)
        )
        { // 修改為你的 Redis 伺服器地址與端口
            // 如果需要密碼驗證，請提供密碼
//            jedis.auth("123456");


            String key = "test";
            assertEquals("test", key);
//            // Act
            jedis.set("testKey", "testValue");
            String result = jedis.get("testKey");
//
//            // Assert
            assertEquals("testValue", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Redis connection or operation failed", e);
        }
    }
}

