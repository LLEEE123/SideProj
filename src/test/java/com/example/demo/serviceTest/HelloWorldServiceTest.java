package com.example.demo.serviceTest;

import com.example.demo.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloWorldServiceTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    void shouldReturnHelloWorldWhenSayHelloIsCalled() {
        // Act
        String result = helloWorldService.sayHello();

        // Assert
        assert(result.equals("Hello World Testing Error!"));
    }
}
