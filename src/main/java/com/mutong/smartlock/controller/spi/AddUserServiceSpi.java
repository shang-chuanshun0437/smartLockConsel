package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockAssert;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.AddUserService;
import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.service.spi.UserManageSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/user")
public class AddUserServiceSpi implements AddUserService
{
    private Logger logger = LoggerFactory.getLogger(AddUserServiceSpi.class);
    @Autowired
    private UserManageSpi userManageSpi;

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @Override
    public AddUserResponse addUser(@RequestBody @Valid AddUserRequest request)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter AddUserServiceSpi,login name:{}",request.toString());
        }

        AddUserResponse response = new AddUserResponse();
        Result result = new Result();

        try
        {
            response = userManageSpi.addUser(request);
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
            logger.error(e.getMessage());
        }
        return response;
    }
}
