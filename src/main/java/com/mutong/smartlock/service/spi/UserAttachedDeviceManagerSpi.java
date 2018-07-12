package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.*;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.service.UserAttachedDeviceManagerService;
import com.mutong.smartlock.service.common.UserAttachedDevice;
import com.mutong.smartlock.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserAttachedDeviceManagerSpi implements UserAttachedDeviceManagerService
{
    private Logger logger = LoggerFactory.getLogger(UserAttachedDeviceManagerSpi.class);

    @Autowired
    private DeviceInfoServiceSpi deviceInfoService;

    @Autowired
    private UserAttachedDeviceSpi userAttachedDevice;

    //自己为自己绑定设备：该设备下没有任何用户或者自己为该设备的主用户，绑定成功
    @Override
    @Transactional
    public BindDeviceResponse bindDevice(BindDeviceRequest request)
    {
        logger.debug("inter bindDevice(),device name:{}",request.toString());

        BindDeviceResponse response = new BindDeviceResponse();
        Result result = new Result();
        result.setRetmsg("success");
        result.setRetcode(ErrorCode.SUCCESS);

        response.setResult(result);

        DeviceInfo dbDeviceInfo = deviceInfoService.findByDeviceNum(request.getDeviceNum());

        //数据库中没有该设备
        LockAssert.isTrue(dbDeviceInfo != null,ErrorCode.DeviceErrorCode.DEVICE_NOT_EXIT,"device not exit in mysql");

        //判断设备的主用户非空，且主用户不等于请求中传过来的用户名
        String mainUser = dbDeviceInfo.getUserName();
        if(!StringUtils.isEmpty(mainUser) && !request.getPhoneNum().equals(mainUser))
        {
            throw new LockException(ErrorCode.DeviceErrorCode.MAIN_USER_MISSMATCH,"device admin user dismatch");
        }

        //进行绑定操作
        dbDeviceInfo.setUserName(request.getPhoneNum());
        dbDeviceInfo.setDeviceName(request.getDeviceName());
        deviceInfoService.save(dbDeviceInfo);

        //如果用户关联设备表中已经存在该条记录，那么就更新这条记录
        UserAttachedDeviceInfo dbUserAttachedDeviceInfo = userAttachedDevice.findByDeviceNumAndUserName(request.getDeviceNum(),request.getPhoneNum());
        if (dbUserAttachedDeviceInfo == null)
        {
            dbUserAttachedDeviceInfo = new UserAttachedDeviceInfo();
        }
        dbUserAttachedDeviceInfo.setDeviceNum(request.getDeviceNum());
        dbUserAttachedDeviceInfo.setUserName(request.getPhoneNum());
        dbUserAttachedDeviceInfo.setUserType(Constant.MAIN_USER);
        dbUserAttachedDeviceInfo.setAssociateTime(DateUtil.yyyyMMddHHmm());
        dbUserAttachedDeviceInfo.setDeviceName(request.getDeviceName());
        dbUserAttachedDeviceInfo.setDeviceMac(dbDeviceInfo.getBluetoothMac());
        dbUserAttachedDeviceInfo.setVersion(dbDeviceInfo.getVersion());
        dbUserAttachedDeviceInfo.setMainUser(dbDeviceInfo.getUserName());

        userAttachedDevice.save(dbUserAttachedDeviceInfo);

        response.setBloothMac(dbDeviceInfo.getBluetoothMac());
        response.setAttachedTime(dbUserAttachedDeviceInfo.getAssociateTime());

        if (logger.isDebugEnabled())
        {
            logger.debug("exit bindDevice(),device name:{}",request.toString());
        }

        return response;
    }

    /*查询用户绑定的设备
    *1、自己名下的设备
    * 2、自己管理的设备
    **/
    @Override
    public QueryUserAttachedDeviceRespose queryUserAttachedDevice(QueryUserAttachedDeviceRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter queryUserAttachedDevice(),user name:{}",request.getPhoneNum());
        }

        QueryUserAttachedDeviceRespose respose = new QueryUserAttachedDeviceRespose();
        Result result = new Result();
        result.setRetcode(ErrorCode.SUCCESS);
        result.setRetmsg("success");

        respose.setResult(result);

        String phoneNum = request.getPhoneNum();
        List<UserAttachedDeviceInfo> userAttachedDeviceInfos = userAttachedDevice.findByUserNameOrMainUser(phoneNum,phoneNum);

        if(userAttachedDeviceInfos != null)
        {
            UserAttachedDevice[] userAttachedDevices = new UserAttachedDevice[userAttachedDeviceInfos.size()];
            for (int i = 0;i < userAttachedDeviceInfos.size();i++)
            {
                UserAttachedDeviceInfo userAttachedDeviceInfo = userAttachedDeviceInfos.get(i);

                userAttachedDevices[i] = new UserAttachedDevice();

                userAttachedDevices[i].setUserName(userAttachedDeviceInfo.getUserName());
                userAttachedDevices[i].setMainName(userAttachedDeviceInfo.getMainUser());
                userAttachedDevices[i].setBloothMac(userAttachedDeviceInfo.getDeviceMac());
                userAttachedDevices[i].setDeviceName(userAttachedDeviceInfo.getDeviceName());
                userAttachedDevices[i].setDeviceNum(userAttachedDeviceInfo.getDeviceNum());
                userAttachedDevices[i].setUserType(userAttachedDeviceInfo.getUserType());
                userAttachedDevices[i].setVersion(userAttachedDeviceInfo.getVersion());
                userAttachedDevices[i].setAssociateTime(userAttachedDeviceInfo.getAssociateTime());
            }

            respose.setUserAttachedDevices(userAttachedDevices);
        }

        logger.debug("exit queryUserAttachedDevice(),user name:{}",request.getPhoneNum());

        return  respose;
    }
}
