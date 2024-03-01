package com.xingchen.openplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisRefreshTTLConfig implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 如果用户已经登录，则刷新 Redis 中用户值的 TTL
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = redisTemplate.opsForValue().get("USER_LOGIN");
        if (user != null) {
            redisTemplate.expire("USER_LOGIN", 1, TimeUnit.DAYS);
        }
        return true;
    }
}