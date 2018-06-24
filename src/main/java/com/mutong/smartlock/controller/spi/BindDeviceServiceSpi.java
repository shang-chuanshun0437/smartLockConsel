package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.util.LoginUtil;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.BindDeviceService;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.interceptor.DeviceAnnotation;
import com.mutong.smartlock.service.spi.UserAttachedDeviceManagerSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/device")
public class BindDeviceServiceSpi implements BindDeviceService
{
    private Logger logger = LoggerFactory.getLogger(BindDeviceServiceSpi.class);

    @Autowired
    private UserAttachedDeviceManagerSpi userAttachedDeviceManager;

    @Autowired
    private LoginUtil loginUtil;

    @DeviceAnnotation
    @RequestMapping(value = "/bindDevice",method = RequestMethod.POST)
    @Override
    public BindDeviceResponse bindDevice(@RequestBody @Valid BindDeviceRequest request)
    {
        logger.debug("inter bindDevice(),user name:{},deviceNmae:{}",request.getUserName(),request.getDeviceName());
        BindDeviceResponse response = new BindDeviceResponse();

        try
        {
            //校验用户是否登录
            LockAssert.isTrue(loginUtil.isLogin(request.getUserName(),request.getToken()),ErrorCode.NOT_LOGIN,"user not login");

            BindDeviceResponse userAttachedDeviceResponse = userAttachedDeviceManager.bindDevice(request);
            response = userAttachedDeviceResponse;
        }
        catch (LockException e)
        {
            Result result = new Result();
            result.setRetcode(e.getMessage());
            response.setResult(result);
            logger.error("bind device error,user name:{},token:{},error msg:{}",request.getUserName(),request.getToken(),e.toString());
        }

        return response;
    }
}
