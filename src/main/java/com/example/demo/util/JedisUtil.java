package com.example.demo.util;
import redis.clients.jedis.Jedis;


public class JedisUtil {

    public void jedisExample() {
        try (Jedis jedis = new Jedis("redis", 6379);){
            // 建立 Jedis 連接到 Redis
//          Jedis jedis = new Jedis("redis", 6379); // Redis 容器的服務名為 redis
            jedis.auth("yourpassword"); // 如果有設定密碼，請提供密碼

            // 存儲數據到 Redis
            jedis.set("key", "value");

            // 讀取 Redis 中的數據
            String value = jedis.get("key");
            System.out.println("Value from Redis: " + value);

//          jedis.close(); // 關閉連接
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
