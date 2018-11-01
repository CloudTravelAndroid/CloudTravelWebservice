package com.cloudtravel.cloudtravelwebservice.Util;

import com.cloudtravel.cloudtravelwebservice.Constant.RedisConstant;
import com.cloudtravel.cloudtravelwebservice.Constant.TokenConstant;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

public class RedisUtil {

    public static Integer getUserIdFromRequestHeader(StringRedisTemplate redisTemplate, HttpServletRequest request) {
        String token = request.getHeader(TokenConstant.TOKEN);
        Integer userId;
        try {
            userId = Integer.valueOf(redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_FORMAT, token)));
        } catch (NumberFormatException e) {
            return null;
        }
        return userId;
    }

    public static void deleteTokenFromRequestHeader(StringRedisTemplate redisTemplate, HttpServletRequest request) {
        String token = request.getHeader(TokenConstant.TOKEN);
        redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_FORMAT, token));
    }
}
