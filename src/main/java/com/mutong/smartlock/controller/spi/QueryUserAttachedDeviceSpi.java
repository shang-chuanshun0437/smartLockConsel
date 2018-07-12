package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
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
            logger.debug("inter queryUserDevices(),user name:{}",request.getPhoneNum());
        }

        QueryUserAttachedDeviceRespose respose = new QueryUserAttachedDeviceRespose();
        Result result = new Result();
        respose.setResult(result);

        try
        {
            boolean isLogin = loginUtil.isLogin(request.getPhoneNum(),request.getToken());
            LockAssert.isTrue(isLogin,ErrorCode.NOT_LOGIN,"user not login");
            //去数据库查询
            respose = userAttachedDeviceManager.queryUserAttachedDevice(request);
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
            logger.error("queryUserDevices failed,",e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            logger.error("queryUserDevices failed exception:",e.getMessage());
        }

        if( logger.isDebugEnabled() )
        {
            logger.debug("exit queryUserDevices(),user name:{}",request.getPhoneNum());
        }
        return respose;
    }
}
