package com.Sidhu.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void testSendMail(){
//        redisTemplate.opsForValue().set("email","sidhushingade@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a=1;
    }
}
