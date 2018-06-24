package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.QueryUserAttachedDeviceService;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;
import com.mutong.smartlock.service.spi.UserAttachedDeviceManagerSpi;
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
@RequestMapping("/v1/query")
public class QueryUserAttachedDeviceSpi implements QueryUserAttachedDeviceService
{
    private Logger logger = LoggerFactory.getLogger(QueryUserAttachedDeviceSpi.class);

    @Autowired
    private UserAttachedDeviceManagerSpi userAttachedDeviceManager;

    @Autowired
    private LoginUtil loginUtil;

    @RequestMapping(value = "/userDevices",method = RequestMethod.POST)
    @Override
    public QueryUserAttachedDeviceRespose queryUserDevices(@RequestBody @Valid QueryUserAttachedDeviceRequest request)
    {
        if( logger.isDebugEnabled() )
        {
            logger.debug("inter queryUserDevices(),user name:{}",request.getUserName());
        }

        QueryUserAttachedDeviceRespose respose = new QueryUserAttachedDeviceRespose();
        Result result = new Result();
        result.setRetmsg("sucess");
        result.setRetcode(ErrorCode.SUCCESS);

        respose.setResult(result);

        boolean isLogin = loginUtil.isLogin(request.getUserName(),request.getToken());

        if(!isLogin)
        {
            result.setRetcode(ErrorCode.NOT_LOGIN);
            result.setRetmsg("user not login");
            return respose;
        }

        //去数据库查询
        respose = userAttachedDeviceManager.queryUserAttachedDevice(request);

        if( logger.isDebugEnabled() )
        {
            logger.debug("exit queryUserDevices(),user name:{}",request.getUserName());
        }
        return respose;
    }
}
