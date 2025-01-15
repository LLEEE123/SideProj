package com.example.demo.service;

import com.example.demo.util.RedisClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Component
public class HelloWorldService {

//    @Autowired
//    RedisClient redisClient;

    public String sayHello() {
        return "Hello World Testing for local!";
    }
    public String sayHelloJedis() {
//        return "Hello World 123";
        return "Hello World Testing, success time --> " + someMethod() +"!";
    }

    public int someMethod() {
        Jedis jedis = RedisClient.getInstance();
        String result = "1";
        if(jedis.get("successTime") == null){
            jedis.set("successTime", String.valueOf(1));

        }else{
            result = jedis.get("successTime");
            jedis.set("successTime", String.valueOf(Integer.valueOf(result)+1));
            result = String.valueOf(Integer.parseInt(result)+1);
        }
        return Integer.parseInt(result);
    }

}
