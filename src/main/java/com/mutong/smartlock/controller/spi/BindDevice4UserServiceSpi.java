package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.BindDevice4UserService;
import com.mutong.smartlock.controller.BindDeviceService;
import com.mutong.smartlock.controller.request.BindDevice4UserRequest;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.response.BindDevice4UserResponse;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.entity.UserInfo;
import com.mutong.smartlock.interceptor.DeviceAnnotation;
import com.mutong.smartlock.service.spi.DeviceInfoServiceSpi;
import com.mutong.smartlock.service.spi.UserAttachedDeviceManagerSpi;
import com.mutong.smartlock.service.spi.UserInfoServiceSpi;
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
@RequestMapping("/v1/device")
public class BindDevice4UserServiceSpi implements BindDevice4UserService
{
    private Logger logger = LoggerFactory.getLogger(BindDevice4UserServiceSpi.class);

    @Autowired
    private UserAttachedDeviceManagerSpi userAttachedDeviceManager;

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private DeviceInfoServiceSpi deviceInfoService;

    @Autowired
    private UserInfoServiceSpi userInfoService;

    @RequestMapping(value = "/bindDevice4User",method = RequestMethod.POST)
    @Override
    public BindDevice4UserResponse bindDevice4User(@RequestBody @Valid BindDevice4UserRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter bindDevice4User(),{}",request.toString());
        }

        BindDevice4UserResponse response = new BindDevice4UserResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            //校验用户是否登录
            LockAssert.isTrue(loginUtil.isLogin(request.getPhoneNum(),request.getToken()),ErrorCode.NOT_LOGIN,"user not login");

            //校验用户是否为该设备的管理员
            DeviceInfo deviceInfo = deviceInfoService.findByDeviceNum(request.getDeviceNum());
            LockAssert.isTrue(deviceInfo != null && deviceInfo.getUserName().equals(request.getPhoneNum()),
                    ErrorCode.DeviceErrorCode.MAIN_USER_MISSMATCH,"main user mismatch.");

            //校验待绑定的用户是否已注册
            UserInfo userInfo = userInfoService.findByPhoneNum(request.getBindPhoneNum());
            LockAssert.isTrue(userInfo != null,ErrorCode.USERPHONE_NOT_EXIST,"user not exist.");

            response = userAttachedDeviceManager.bindDevice4User(deviceInfo,request.getBindPhoneNum(),request.getValidDate());
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
            logger.error("bind device for user failed,{}",e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            logger.error("bind device for user failed,{}",e.getMessage());
        }

        return response;
    }
}
