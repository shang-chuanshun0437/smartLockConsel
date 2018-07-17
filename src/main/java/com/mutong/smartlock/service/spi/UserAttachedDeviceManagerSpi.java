package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.*;
import com.mutong.smartlock.controller.request.BindDevice4UserRequest;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.BindDevice4UserResponse;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.controller.response.DeleteDeviceOfUserResponse;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.dao.entity.UserInfo;
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

    @Autowired
    private UserInfoServiceSpi userInfoService;

    //自己为自己绑定设备：该设备下没有任何用户或者自己为该设备的主用户，绑定成功
    @Override
    @Transactional
    public BindDeviceResponse bindDevice(BindDeviceRequest request)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter bindDevice(),device name:{}",request.toString());
        }

        BindDeviceResponse response = new BindDeviceResponse();
        Result result = new Result();
        result.setRetmsg("success");
        result.setRetcode(ErrorCode.SUCCESS);

        response.setResult(result);

        DeviceInfo dbDeviceInfo = deviceInfoService.findByDeviceNum(request.getDeviceNum());

        //数据库中没有该设备
        LockAssert.isTrue(dbDeviceInfo != null,ErrorCode.DeviceErrorCode.DEVICE_NOT_EXIT,"device not exit in mysql");

        //判断设备的主用户非空，且主用户不等于请求中传过来的用户名
        String mainUser = dbDeviceInfo.getPhoneNum();
        if(!StringUtils.isEmpty(mainUser) && !request.getPhoneNum().equals(mainUser))
        {
            throw new LockException(ErrorCode.DeviceErrorCode.MAIN_USER_MISSMATCH,"device admin user dismatch");
        }
        //去数据库中查询用户详情
        UserInfo userInfo = userInfoService.findByPhoneNum(request.getPhoneNum());
        LockAssert.isTrue(userInfo != null,ErrorCode.USERPHONE_NOT_EXIST,"user not exist.");
        //进行绑定操作
        dbDeviceInfo.setPhoneNum(request.getPhoneNum());
        dbDeviceInfo.setDeviceName(request.getDeviceName());
        deviceInfoService.save(dbDeviceInfo);

        //如果用户关联设备表中已经存在该条记录，那么就更新这条记录
        UserAttachedDeviceInfo dbUserAttachedDeviceInfo = userAttachedDevice.findByDeviceNumAndPhoneNum(request.getDeviceNum(),request.getPhoneNum());
        if (dbUserAttachedDeviceInfo == null)
        {
            dbUserAttachedDeviceInfo = new UserAttachedDeviceInfo();

            dbUserAttachedDeviceInfo.setDeviceNum(request.getDeviceNum());
            dbUserAttachedDeviceInfo.setUserName(userInfo.getUserName());
            dbUserAttachedDeviceInfo.setPhoneNum(userInfo.getPhoneNum());
            dbUserAttachedDeviceInfo.setPhoneNum(request.getPhoneNum());
            dbUserAttachedDeviceInfo.setUserType(Constant.MAIN_USER);
            dbUserAttachedDeviceInfo.setAssociateTime(DateUtil.yyyyMMddHHmm());
            dbUserAttachedDeviceInfo.setDeviceName(request.getDeviceName());
            dbUserAttachedDeviceInfo.setDeviceMac(dbDeviceInfo.getBluetoothMac());
            dbUserAttachedDeviceInfo.setVersion(dbDeviceInfo.getVersion());
            dbUserAttachedDeviceInfo.setMainUser(dbDeviceInfo.getPhoneNum());
        }
        else
        {
            dbUserAttachedDeviceInfo.setDeviceName(request.getDeviceName());
        }

        userAttachedDevice.save(dbUserAttachedDeviceInfo);

        //修改user_device表中所有关联该设备的设备名称
        List<UserAttachedDeviceInfo> list = userAttachedDevice.findByDeviceNum(request.getPhoneNum());
        for (UserAttachedDeviceInfo userAttachedDeviceInfo : list)
        {
            userAttachedDeviceInfo.setDeviceName(request.getDeviceName());
            userAttachedDevice.save(userAttachedDeviceInfo);
        }
        
        response.setBluetoothMac(dbDeviceInfo.getBluetoothMac());
        response.setDeviceVersion(dbDeviceInfo.getVersion());
        response.setAttachedTime(dbUserAttachedDeviceInfo.getAssociateTime());

        if (logger.isDebugEnabled())
        {
            logger.debug("exit bindDevice(),device name:{}",request.toString());
        }

        return response;
    }

    /*查询用户绑定的设备
    *1、自己是普通用户
    * 2、自己是管理员
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
        List<UserAttachedDeviceInfo> userAttachedDeviceInfos = userAttachedDevice.findByPhoneNumOrMainUser(phoneNum,phoneNum);

        if(userAttachedDeviceInfos != null)
        {
            UserAttachedDevice[] userAttachedDevices = new UserAttachedDevice[userAttachedDeviceInfos.size()];
            for (int i = 0;i < userAttachedDeviceInfos.size();i++)
            {
                UserAttachedDeviceInfo userAttachedDeviceInfo = userAttachedDeviceInfos.get(i);

                userAttachedDevices[i] = new UserAttachedDevice();

                userAttachedDevices[i].setUserName(userAttachedDeviceInfo.getUserName());
                userAttachedDevices[i].setPhoneNum(userAttachedDeviceInfo.getPhoneNum());
                userAttachedDevices[i].setMainName(userAttachedDeviceInfo.getMainUser());
                userAttachedDevices[i].setBluetoothMac(userAttachedDeviceInfo.getDeviceMac());
                userAttachedDevices[i].setDeviceName(userAttachedDeviceInfo.getDeviceName());
                userAttachedDevices[i].setDeviceNum(userAttachedDeviceInfo.getDeviceNum());
                userAttachedDevices[i].setUserType(userAttachedDeviceInfo.getUserType());
                userAttachedDevices[i].setVersion(userAttachedDeviceInfo.getVersion());
                userAttachedDevices[i].setAssociateTime(userAttachedDeviceInfo.getAssociateTime());
                userAttachedDevices[i].setValidDate(userAttachedDeviceInfo.getValidDate());
            }

            respose.setUserAttachedDevices(userAttachedDevices);
        }

        if(logger.isDebugEnabled())
        {
            logger.debug("exit queryUserAttachedDevice(),numbers:{}",userAttachedDeviceInfos.size());
        }

        return  respose;
    }

    @Override
    public BindDevice4UserResponse bindDevice4User(DeviceInfo deviceInfo,UserInfo bindUserInfo,String validDate)
    {
        BindDevice4UserResponse response = new BindDevice4UserResponse();
        Result result = new Result();
        response.setResult(result);

        //user_device是否存在绑定关系
        UserAttachedDeviceInfo userAttachedDeviceInfo = userAttachedDevice.findByDeviceNumAndPhoneNum(deviceInfo.getDeviceNum(),bindUserInfo.getPhoneNum());

        if(userAttachedDeviceInfo == null)
        {
            userAttachedDeviceInfo = new UserAttachedDeviceInfo();

            userAttachedDeviceInfo.setAssociateTime(DateUtil.yyyyMMddHHmm());
            userAttachedDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
            userAttachedDeviceInfo.setDeviceMac(deviceInfo.getBluetoothMac());
            userAttachedDeviceInfo.setDeviceNum(deviceInfo.getDeviceNum());
            userAttachedDeviceInfo.setMainUser(deviceInfo.getPhoneNum());
            userAttachedDeviceInfo.setVersion(deviceInfo.getVersion());
            userAttachedDeviceInfo.setValidDate(validDate);
            userAttachedDeviceInfo.setUserName(bindUserInfo.getUserName());
            userAttachedDeviceInfo.setPhoneNum(bindUserInfo.getPhoneNum());
            userAttachedDeviceInfo.setUserType(Constant.SUB_USER);
        }
        else
        {
            userAttachedDeviceInfo.setValidDate(validDate);
        }

        //将数据存入user_device
        userAttachedDevice.save(userAttachedDeviceInfo);

        //将数据返回给前端
        UserAttachedDevice userAttachedDevice = new UserAttachedDevice();

        userAttachedDevice.setValidDate(userAttachedDeviceInfo.getValidDate());
        userAttachedDevice.setAssociateTime(userAttachedDeviceInfo.getAssociateTime());
        userAttachedDevice.setBluetoothMac(userAttachedDeviceInfo.getDeviceMac());
        userAttachedDevice.setDeviceName(userAttachedDeviceInfo.getDeviceName());
        userAttachedDevice.setVersion(userAttachedDeviceInfo.getVersion());
        userAttachedDevice.setDeviceNum(userAttachedDeviceInfo.getDeviceNum());
        userAttachedDevice.setMainName(userAttachedDeviceInfo.getMainUser());
        userAttachedDevice.setUserName(userAttachedDeviceInfo.getUserName());
        userAttachedDevice.setUserType(userAttachedDeviceInfo.getUserType());
        userAttachedDevice.setPhoneNum(userAttachedDeviceInfo.getPhoneNum());

        response.setUserAttachedDevice(userAttachedDevice);

        return response;
    }

    @Transactional
    @Override
    public DeleteDeviceOfUserResponse deleteDevice(String phoneNum,String deletePhoneNum, DeviceInfo deviceInfo)
    {
        DeleteDeviceOfUserResponse response = new DeleteDeviceOfUserResponse();
        Result result = new Result();
        response.setResult(result);
        //1、普通用户，自己删自己
        if (phoneNum.equals(deletePhoneNum) && !phoneNum.equals(deviceInfo.getPhoneNum()))
        {
            userAttachedDevice.deleteByPhoneNumAndDeviceNum(deletePhoneNum,deviceInfo.getDeviceNum());
            return response;
        }
        //2、管理员删普通用户
        if(!phoneNum.equals(deletePhoneNum))
        {
            LockAssert.isTrue(phoneNum.equals(deviceInfo.getPhoneNum()),ErrorCode.DeviceErrorCode.MAIN_USER_MISSMATCH,"main user dismatch.");

            userAttachedDevice.deleteByPhoneNumAndDeviceNum(deletePhoneNum,deviceInfo.getDeviceNum());
            return response;
        }
        //3、管理员自己删自己，那么要求该设备下，没有其他用户

        if(phoneNum.equals(deletePhoneNum) && phoneNum.equals(deviceInfo.getPhoneNum()))
        {
            List<UserAttachedDeviceInfo> userAttachedDeviceInfos = userAttachedDevice.findByDeviceNum(deviceInfo.getDeviceNum());
            LockAssert.isTrue(userAttachedDeviceInfos != null && userAttachedDeviceInfos.size() <= 1,
                    ErrorCode.DeviceErrorCode.OTHER_USERS_EXIST,"the device has other users.");

            //管理员自己删自己，则要把device_info的管理员置空
            deviceInfo.setPhoneNum(null);
            deviceInfo.setDeviceName(null);
            deviceInfoService.save(deviceInfo);

            for (UserAttachedDeviceInfo userAttachedDeviceInfo : userAttachedDeviceInfos)
            {
                userAttachedDevice.deleteById(userAttachedDeviceInfo.getId());
            }
            return response;
        }

        result.setRetcode(ErrorCode.DEFAULT_ERROR);
        return response;
    }
}
