package com.mutong.smartlock.controller.spi;

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
        logger.debug("inter AddUserServiceSpi,login name:{}",request.getPhoneNum());
        return userManageSpi.addUser(request);
    }
}
