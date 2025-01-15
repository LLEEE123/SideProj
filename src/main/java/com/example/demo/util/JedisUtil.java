package com.example.demo.util;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisUtil {

//    private Jedis jedis;
//
//    // 通過構造函數注入 RedisClient
//    @Autowired
//    public JedisUtil(RedisClient redisClient) {
//        this.jedis = redisClient.getInstance();
//    }

    public void jedisExample() {
        try (Jedis jedis = RedisClient.getInstance()){
            // 存儲數據到 Redis
            jedis.set("key", "value");

            // 讀取 Redis 中的數據
            String value = jedis.get("key");
            System.out.println("Value from Redis: " + value);

          jedis.close(); // 關閉連接
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
