package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.BindDeviceService;
import com.mutong.smartlock.controller.DeleteDeviceOfUserService;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.request.DeleteDeviceOfUserRequest;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.controller.response.DeleteDeviceOfUserResponse;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.interceptor.DeviceAnnotation;
import com.mutong.smartlock.service.spi.DeviceInfoServiceSpi;
import com.mutong.smartlock.service.spi.UserAttachedDeviceManagerSpi;
import com.mutong.smartlock.service.spi.UserAttachedDeviceSpi;
import com.mutong.smartlock.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/device")
public class DeleteDeviceOfUserSpi implements DeleteDeviceOfUserService
{
    private Logger logger = LoggerFactory.getLogger(DeleteDeviceOfUserSpi.class);

    @Autowired
    private UserAttachedDeviceManagerSpi userAttachedDeviceManager;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private DeviceInfoServiceSpi deviceInfoService;

    @Autowired
    private UserAttachedDeviceSpi userAttachedDevice;

    @RequestMapping(value = "/deleteDevice",method = RequestMethod.POST)
    @Override
    public DeleteDeviceOfUserResponse deleteDevice(@RequestBody @Valid DeleteDeviceOfUserRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter deleteDevice(),{}",request.toString());
        }

        DeleteDeviceOfUserResponse response = new DeleteDeviceOfUserResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            //校验用户是否登录
            LockAssert.isTrue(loginUtil.isLogin(request.getPhoneNum(),request.getToken()),ErrorCode.NOT_LOGIN,"user not login");

            //校验设备是否存在
            DeviceInfo deviceInfo = deviceInfoService.findByDeviceNum(request.getDeviceNum());
            LockAssert.isTrue(deviceInfo != null,ErrorCode.DeviceErrorCode.DEVICE_NOT_EXIT,"device not exist.");

            //进行删除操作
            response = userAttachedDeviceManager.deleteDevice(request.getPhoneNum(),request.getDeletePhoneNum(),deviceInfo);
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
            logger.error("delete device failed,{}",e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            logger.error("delete device failed,exception:{}",e.getMessage());
        }

        return response;
    }
}
