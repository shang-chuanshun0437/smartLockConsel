package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.jpa.DeviceInfoDao;
import com.mutong.smartlock.service.DeviceManager;
import com.mutong.smartlock.service.request.GetDeviceInfoRequest;
import com.mutong.smartlock.service.response.GetDeviceInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceManagerSpi implements DeviceManager
{
    private Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Override
    public GetDeviceInfoResponse findDeviceByNum(GetDeviceInfoRequest request)
    {
        logger.debug("inter findDeviceByNum(),device name:{}",request.getDeviceNum());

        GetDeviceInfoResponse response = new GetDeviceInfoResponse();
        Result result = new Result();
        result.setRetmsg("success");
        result.setRetcode(ErrorCode.SUCCESS);

        DeviceInfo deviceInfo = deviceInfoDao.findByDeviceNum(request.getDeviceNum());

        response.setDeviceInfo(deviceInfo);

        logger.debug("exit findDeviceByNum(),device name:{}",request.getDeviceNum());

        return response;
    }
}
