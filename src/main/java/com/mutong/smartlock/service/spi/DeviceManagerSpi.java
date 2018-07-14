package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.response.ModifyDeviceNameResponse;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.dao.jpa.DeviceInfoDao;
import com.mutong.smartlock.dao.jpa.UserAttachedDeviceDao;
import com.mutong.smartlock.service.DeviceManager;
import com.mutong.smartlock.service.common.UserAttachedDevice;
import com.mutong.smartlock.service.request.GetDeviceInfoRequest;
import com.mutong.smartlock.service.response.GetDeviceInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceManagerSpi implements DeviceManager
{
    private Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Autowired
    private UserAttachedDeviceDao userAttachedDeviceDao;

    @Override
    public GetDeviceInfoResponse findDeviceByNum(String deviceNum)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter findDeviceByNum(),device name:{}",deviceNum);
        }

        GetDeviceInfoResponse response = new GetDeviceInfoResponse();
        Result result = new Result();
        result.setRetmsg("success");
        result.setRetcode(ErrorCode.SUCCESS);

        DeviceInfo deviceInfo = deviceInfoDao.findByDeviceNum(deviceNum);

        response.setDeviceInfo(deviceInfo);

        if (logger.isDebugEnabled())
        {
            logger.debug("exit findDeviceByNum()");
        }

        return response;
    }

    @Transactional
    @Override
    public ModifyDeviceNameResponse modifyDeviceName(String deviceNum, String newName)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter modifyDeviceName(),device num:{},device name :{}",deviceNum,newName);
        }

        ModifyDeviceNameResponse response = new ModifyDeviceNameResponse();
        Result result = new Result();
        response.setResult(result);
        //device_info
        DeviceInfo deviceInfo = deviceInfoDao.findByDeviceNum(deviceNum);
        LockAssert.isTrue(deviceInfo != null,ErrorCode.DeviceErrorCode.DEVICE_NOT_EXIT,"device not exist.");
        deviceInfo.setDeviceName(newName);
        deviceInfoDao.save(deviceInfo);

        //user_device
        List<UserAttachedDeviceInfo> userAttachedDeviceInfos = userAttachedDeviceDao.findByDeviceNum(deviceNum);

        if (userAttachedDeviceInfos != null)
        {
            for (UserAttachedDeviceInfo userAttachedDeviceInfo : userAttachedDeviceInfos)
            {
                userAttachedDeviceInfo.setDeviceName(newName);
                userAttachedDeviceDao.save(userAttachedDeviceInfo);
            }
        }
        response.setDeviceName(newName);
        return response;
    }
}
