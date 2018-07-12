package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.ModifyUserNameService;
import com.mutong.smartlock.controller.request.ModifyUserNameRequest;
import com.mutong.smartlock.controller.response.ModifyUserNameResponse;
import com.mutong.smartlock.service.spi.UserManageSpi;
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
@RequestMapping("/v1/user")
public class ModifyUserNameSpi implements ModifyUserNameService
{
    private Logger logger = LoggerFactory.getLogger(ModifyUserNameSpi.class);

    @Autowired
    private UserManageSpi userManage;

    @Autowired
    private LoginUtil loginUtil;

    @RequestMapping(value = "/modifyUserName",method = RequestMethod.POST)
    @Override
    public ModifyUserNameResponse modifyUserName(@RequestBody @Valid ModifyUserNameRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter modifyUserName(),{}",request.toString());
        }

        ModifyUserNameResponse response = new ModifyUserNameResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            //首先去校验是否已登录
            boolean isLogin = loginUtil.isLogin(request.getPhoneNum(),request.getToken());
            LockAssert.isTrue(isLogin,ErrorCode.NOT_LOGIN,"user not login.");

            response = userManage.modifyUserName(request.getPhoneNum(),request.getNewUserName());
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
