package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.Constant;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;
import com.mutong.smartlock.service.DeviceManager;
import com.mutong.smartlock.service.VerifyCodeManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeManagerSpi implements VerifyCodeManage
{
    private Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public GetVerifyCodeResponse getVerifyCode(GetVerifyCodeRequest request)
    {
        if( logger.isDebugEnabled() )
        {
            logger.debug("inter getVerifyCode(),phone num:",request.getPhoneNum());
        }
        GetVerifyCodeResponse response = new GetVerifyCodeResponse();
        Result result = new Result();
        response.setResult(result);
        //去redis中校验验证码是否存在
        String verifyCode = redisTemplate.opsForValue().get(request.getPhoneNum() + Constant.VERIFY_CODE);
        if (!StringUtils.isEmpty(verifyCode))
        {
            //验证码已存在
            return response;
        }

        //验证码不存在，则产生6位的验证码
        //Todo 模拟产生验证码:123456  存活期90s
        redisTemplate.opsForValue().set(request.getPhoneNum() + Constant.VERIFY_CODE,"123456",90, TimeUnit.SECONDS);
        return response;
    }
}
