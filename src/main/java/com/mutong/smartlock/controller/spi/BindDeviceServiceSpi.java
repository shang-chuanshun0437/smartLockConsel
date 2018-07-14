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
        if(logger.isDebugEnabled())
        {
            logger.debug("inter bindDevice(),{}",request.toString());
        }

        BindDeviceResponse response = new BindDeviceResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            //校验用户是否登录
            LockAssert.isTrue(loginUtil.isLogin(request.getPhoneNum(),request.getToken()),ErrorCode.NOT_LOGIN,"user not login");

            response = userAttachedDeviceManager.bindDevice(request);
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
            logger.error("bind device failed,{}",e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            logger.error("bind device failed,{}",e.getMessage());
        }

        return response;
    }
}
