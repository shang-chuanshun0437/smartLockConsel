package com.mutong.smartlock.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class DeviceInterceptor
{
    private Logger logger = LoggerFactory.getLogger(DeviceInterceptor.class);

    @Pointcut("@annotation(com.mutong.smartlock.interceptor.DeviceAnnotation)")
    public void devicePonit()
    {

    }

    @Before("devicePonit()")
    public void doBefore(JoinPoint joinPoint) throws Throwable
    {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.debug("inter DeviceInterceptor,request url:{},remote ip:{},headers:{}",
                request.getRequestURI(),request.getRemoteAddr(),request.getHeader("phoneNum"));

    }

}
