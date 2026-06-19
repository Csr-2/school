package com.test.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
        ApplicationContext ctx = SpringApplication.run(StudentApplication.class, args);

        // 临时测试：看看有没有 RedisTemplate 这个 Bean
        boolean hasRedis = ctx.containsBean("redisTemplate");
        System.out.println("RedisTemplate是否存在: " + hasRedis);
    }

}
