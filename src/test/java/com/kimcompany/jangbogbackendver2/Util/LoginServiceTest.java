package com.kimcompany.jangbogbackendver2.Util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private RedisTemplate<String, Object> restTemplate;

    @Test
    public void testExpire() throws InterruptedException {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("key", "val");
        restTemplate.opsForHash().put("1", "2",linkedHashMap);
        restTemplate.expire("2", 1, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        System.out.println(restTemplate.opsForHash().entries("1"));

    }
}