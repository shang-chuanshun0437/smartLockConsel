package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.QueryOpenDoorHistoryService;
import com.mutong.smartlock.controller.QueryUserAttachedDeviceService;
import com.mutong.smartlock.controller.request.QueryOpenDoorHistoryReq;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.QueryOpenDoorHistoryResp;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;
import com.mutong.smartlock.service.spi.GetOpenDoorHistorySpi;
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
public class QueryOpenDoorHistorySpi implements QueryOpenDoorHistoryService
{
    private Logger logger = LoggerFactory.getLogger(QueryOpenDoorHistorySpi.class);

    @Autowired
    private LoginUtil loginUtil;

    @Autowired
    private GetOpenDoorHistorySpi getOpenDoorHistory;

    @RequestMapping(value = "/openDoorHistory",method = RequestMethod.POST)
    @Override
    public QueryOpenDoorHistoryResp queryOpenDoorHistory(@RequestBody @Valid QueryOpenDoorHistoryReq request)
    {
        if( logger.isDebugEnabled() )
        {
            logger.debug("inter queryOpenDoorHistory(),user name:{}",request.getUserName());
        }

        QueryOpenDoorHistoryResp respose = new QueryOpenDoorHistoryResp();
        Result result = new Result();
        respose.setResult(result);

        boolean isLogin = loginUtil.isLogin(request.getUserName(),request.getToken());

        if(!isLogin)
        {
            result.setRetcode(ErrorCode.NOT_LOGIN);
            result.setRetmsg("user not login");
            return respose;
        }
        respose = getOpenDoorHistory.findOpenDoorHistory(request.getDeviceNum(),request.getPage());

        return respose;
    }
}
