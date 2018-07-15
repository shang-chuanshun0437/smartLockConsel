package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.ModifyDeviceNameService;
import com.mutong.smartlock.controller.request.ModifyDeviceNameRequest;
import com.mutong.smartlock.controller.response.ModifyDeviceNameResponse;
import com.mutong.smartlock.controller.response.ModifyUserNameResponse;
import com.mutong.smartlock.service.response.GetDeviceInfoResponse;
import com.mutong.smartlock.service.spi.DeviceManagerSpi;
import com.mutong.smartlock.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/modify")
public class ModifyDeviceNameSpi implements ModifyDeviceNameService
{
    private Logger logger = LoggerFactory.getLogger(ModifyDeviceNameSpi.class);

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private DeviceManagerSpi deviceManager;

    @RequestMapping(value = "/modifyDeviceName",method = RequestMethod.POST)
    @Override
    public ModifyDeviceNameResponse modifyDeviceName(@RequestBody @Valid ModifyDeviceNameRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter modifyUserName(),{}",request.toString());
        }

        ModifyDeviceNameResponse response = new ModifyDeviceNameResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            //首先去校验是否已登录
            boolean isLogin = loginUtil.isLogin(request.getPhoneNum(),request.getToken());
            LockAssert.isTrue(isLogin,ErrorCode.NOT_LOGIN,"user not login.");

            //校验是否为该设备的管理员
            GetDeviceInfoResponse deviceInfoResponse = deviceManager.findDeviceByNum(request.getDeviceNum());
            LockAssert.isTrue(deviceInfoResponse.getDeviceInfo() != null,ErrorCode.DeviceErrorCode.DEVICE_NOT_EXIT,"device not exist");
            LockAssert.isTrue(deviceInfoResponse.getDeviceInfo().getPhoneNum().equals(request.getPhoneNum()),
                    ErrorCode.DeviceErrorCode.MAIN_USER_MISSMATCH,"user is not the main user of device");

            //修改设备名称
            response = deviceManager.modifyDeviceName(request.getDeviceNum(),request.getDeviceName());
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());

            logger.error("modifyUserName failed,{}",e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            logger.error("modifyUserName exception,{}",e.getMessage());
        }
        return response;
    }
}
