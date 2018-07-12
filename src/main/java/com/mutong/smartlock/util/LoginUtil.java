package com.mutong.smartlock.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginUtil
{
    @Autowired
    private  StringRedisTemplate redisTemplate;

    public boolean isLogin(String phoneNum,String token)
    {
        if(StringUtils.isEmpty(token))
        {
            return false;
        }

        HashOperations<String, String, String> hashOperations= redisTemplate.opsForHash();
        Map<String,String> resultMap = hashOperations.entries(phoneNum);

        String redisToken = resultMap.get("token");

        if( !token.equals(redisToken))
        {
            return false;
        }

        return true;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
