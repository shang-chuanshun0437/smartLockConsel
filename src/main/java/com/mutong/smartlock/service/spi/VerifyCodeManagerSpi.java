package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.Constant;
import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.request.CheckVerifyCodeRequest;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.response.CheckVerifyCodeResponse;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;
import com.mutong.smartlock.dao.entity.UserInfo;
import com.mutong.smartlock.service.DeviceManager;
import com.mutong.smartlock.service.VerifyCodeManage;
import com.mutong.smartlock.service.exception.UserManageExiception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeManagerSpi implements VerifyCodeManage
{
    private Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoServiceSpi userInfoServiceSpi;

    @Override
    public GetVerifyCodeResponse getVerifyCode(GetVerifyCodeRequest request)
    {
        if( logger.isDebugEnabled() )
        {
            logger.debug("inter getVerifyCode(),phone num:{}",request.getPhoneNum());
        }
        GetVerifyCodeResponse response = new GetVerifyCodeResponse();
        Result result = new Result();
        response.setResult(result);

        //校验手机号是否已经注册
        UserInfo userInfo = userInfoServiceSpi.findByPhoneNum(request.getPhoneNum());
        LockAssert.isTrue(userInfo == null,ErrorCode.PHONE_NUM_EXIST,"phone num has alreday exits");

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

    @Override
    public CheckVerifyCodeResponse checkVerifyCode(CheckVerifyCodeRequest request)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter checkVerifyCode():{}",request.toString());
        }
        CheckVerifyCodeResponse response = new CheckVerifyCodeResponse();
        Result result = new Result();
        response.setResult(result);

        //去redis中校验验证码是否存在、正确
        String verifyCode = redisTemplate.opsForValue().get(request.getPhoneNum() + Constant.VERIFY_CODE);

        LockAssert.isTrue(verifyCode != null,ErrorCode.VERIFY_CODE_NULL,"verify code not exist");
        LockAssert.isTrue(verifyCode.equals(request.getVerifyCode()),ErrorCode.VERIFY_CODE_ERROR,"verify code is error");

        //验证码正确的凭证,存入redis，生命期2个小时
        String voucher = getRandom(6);
        redisTemplate.opsForValue().set(request.getPhoneNum() + Constant.VERIFY_VOUCHER,voucher,2*60*60, TimeUnit.SECONDS);
        response.setVoucher(voucher);

        return response;
    }

    //digit 获取的随机数的位数
    private String getRandom(int digit)
    {
        StringBuffer buffer = new StringBuffer();

        Random random = new Random();

        for(int i = 0;i < digit;i++)
        {
            buffer.append(random.nextInt(10));
        }

        return buffer.toString();
    }
}
