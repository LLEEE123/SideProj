package com.example.demo.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Slf4j
@Component
public class RedisClient {

    private static Jedis jedis;
    private static String appEnv;

    @Value("${app.env}")
    private String env;

    // 私有構造函數，防止外部創建新的實例
    private RedisClient() {
    }

    @PostConstruct
    public void init() {
        // 設置 appEnv
        appEnv = env;

        if (jedis == null) {
            synchronized (RedisClient.class) {
                if (jedis == null) {
//                    String redisIp = System.getenv("REDIS_IP");
//                    String redisEnv = redisIp;
//
//                    switch (appEnv) {
//                        case "local":
//                            // localhost本機測試時的配置
//                            redisEnv = "localhost";
//                            break;
//                        case "localBrowser":
//                            // localhost本機瀏覽器測試時的配置
//                            redisEnv = "redis";
//                            break;
//                        case "gitAws":
//                            // github ci測試時的配置
//                            redisEnv = redisIp;
//                            break;
//                        default:
//                            redisEnv = redisIp;
//                            break;
//                    }
//                    log.info("appEnv = {}", appEnv);
//                    jedis = new Jedis(redisEnv, 6379);
                    jedis = new Jedis("172.18.0.2", 6379);
//                    jedis.auth("yourpassword"); // 如果有設定密碼，請提供密碼
                }
            }
        }
    }

    // 單例模式：獲取 Jedis 實例
    public static Jedis getInstance() {
        if (jedis == null) {
            throw new IllegalStateException("RedisClient not initialized.");
        }
        return jedis;
    }

    // 在 Spring 容器關閉時關閉 Jedis 連接
    @PreDestroy
    public void close() {
        if (jedis != null) {
            try {
                jedis.close();
//                log.info("Jedis connection closed.");
            } catch (Exception e) {
//                log.error("Error while closing Jedis connection", e);
            }
        }
    }
}

