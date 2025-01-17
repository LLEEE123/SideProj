package com.example.demo.JedisTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JedisIntegrationTest {
    @Value("${app.env}")
    public String appEnv;

    @Test
    void shouldStoreAndRetrieveValueFromRedis() {
        String redisIp = System.getenv("REDIS_IP");
        //local測試需要
        appEnv = "localBrowser";
        String redisEnv = redisIp;
        switch (appEnv) {
            case "local":
                //                    localhost本機測試時的配置
                redisEnv = "localhost";
                break;
            case "localBrowser":
                //                    localhost本機瀏覽器測試時的配置
                redisEnv = "redis";
                break;
            case "gitAws":
                //                    github ci測試時的配置
                redisEnv = redisIp;
                break;
            default:
                //                    github ci測試時的配置
                redisEnv = redisIp;
                break;
        }

        try(
                Jedis jedis = new Jedis(redisIp, 6379);
        )
        {
            String role = "admin";


            // 修改為你的 Redis 伺服器地址與端口
            // 如果需要密碼驗證，請提供密碼
//            jedis.auth("123456");


            String key = "test";
            assertEquals("test", key);
//            // Act
            jedis.set("testKey", "testValue");
            String result = jedis.get("testKey");
            System.out.println("jedis = " + jedis);
            System.out.println("result = " + result);
//            // Assert
            assertEquals("testValue", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Redis connection or operation failed", e);
        }
    }
}

