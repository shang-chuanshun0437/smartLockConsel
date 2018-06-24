package com.mutong.smartlock.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//使用该注解的方法，都会被DeviceInterceptor拦截器拦截
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DeviceAnnotation
{
    String value() default "";
}
