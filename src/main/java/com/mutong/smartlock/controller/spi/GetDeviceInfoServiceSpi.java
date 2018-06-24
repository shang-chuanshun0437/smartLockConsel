package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.GetDeviceInfoService;
import com.mutong.smartlock.service.response.GetDeviceInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GetDeviceInfo")
public class GetDeviceInfoServiceSpi implements GetDeviceInfoService
{
    @GetMapping("/all")
    @Override
    public GetDeviceInfoResponse getAllDeviceInfo()
    {
        Result result = new Result();
        result.setRetmsg("Get device info success");

        GetDeviceInfoResponse response = new GetDeviceInfoResponse();
        response.setResult(result);
        return response;
    }

    @GetMapping("/DeviceName")
    @Override
    public GetDeviceInfoResponse getDevicInfoByDeviceNum(long deviceNum) {
        return null;
    }

    @GetMapping("/like")
    @Override
    public GetDeviceInfoResponse getDeviceInfoBylike(long deviceNum) {
        return null;
    }


}
