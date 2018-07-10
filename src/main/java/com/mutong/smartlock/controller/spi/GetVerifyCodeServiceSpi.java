package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.controller.AddUserService;
import com.mutong.smartlock.controller.GetVerifyCodeService;
import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;
import com.mutong.smartlock.service.spi.UserManageSpi;
import com.mutong.smartlock.service.spi.VerifyCodeManagerSpi;
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
public class GetVerifyCodeServiceSpi implements GetVerifyCodeService
{
    private Logger logger = LoggerFactory.getLogger(GetVerifyCodeServiceSpi.class);
    @Autowired
    private VerifyCodeManagerSpi verifyCodeManagerSpi;

    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.POST)
    @Override
    public GetVerifyCodeResponse getVerifyCode(@RequestBody @Valid GetVerifyCodeRequest request)
    {
        return verifyCodeManagerSpi.getVerifyCode(request);
    }
}
