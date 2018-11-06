package com.cloudtravel.cloudtravelwebservice.Aspect;

import com.cloudtravel.cloudtravelwebservice.Constant.RedisConstant;
import com.cloudtravel.cloudtravelwebservice.Constant.TokenConstant;
import com.cloudtravel.cloudtravelwebservice.Exception.UnauthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class UserVerifyAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.cloudtravel.cloudtravelwebservice.Controller.*.*(..))" +
            "&& !execution(public * com.cloudtravel.cloudtravelwebservice.Controller.UserController.signIn(..))" +
            "&& !execution(public * com.cloudtravel.cloudtravelwebservice.Controller.UserController.signUp(..))")
    private void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(TokenConstant.TOKEN);
        if (token == null) {
            throw new UnauthorizedException();
        }
        String value = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_FORMAT, token));
        if (StringUtils.isEmpty(value)) {
            throw new UnauthorizedException();
        }
    }
}
