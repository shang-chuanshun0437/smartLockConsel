package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.LoginService;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.service.spi.UserManageSpi;
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
public class LoginServiceSpi implements LoginService
{
    private Logger logger = LoggerFactory.getLogger(AddUserServiceSpi.class);

    @Autowired
    private UserManageSpi userManage;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @Override
    public LoginResponse login(@RequestBody @Valid LoginRequest request)
    {
        LoginResponse response = new LoginResponse();
        Result result = new Result();
        response.setResult(result);

        try
        {
            response = userManage.login(request);
        }
        catch (LockException e)
        {
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());
            logger.error(e.getMsg());
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.DEFAULT_ERROR);
            result.setRetmsg(e.getMessage());
            logger.error(e.getMessage());
        }

        return response;
    }
}
