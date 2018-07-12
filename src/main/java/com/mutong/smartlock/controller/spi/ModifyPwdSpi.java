package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.LoginService;
import com.mutong.smartlock.controller.ModifyPwdService;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.request.ModifyPwdRequest;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.controller.response.ModifyPwdResponse;
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
public class ModifyPwdSpi implements ModifyPwdService
{
    private Logger logger = LoggerFactory.getLogger(ModifyPwdSpi.class);

    @Autowired
    private UserManageSpi userManage;

    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    @Override
    public ModifyPwdResponse modifyPwd(@RequestBody @Valid ModifyPwdRequest request)
    {
        ModifyPwdResponse response = new ModifyPwdResponse();

        try
        {
            response = userManage.modifyPwd(request);
        }
        catch (LockException e)
        {
            Result result = new Result();
            result.setRetcode(e.getCode());
            result.setRetmsg(e.getMsg());

            response.setResult(result);
            logger.error("modifyPwd failed,{}",e.getMsg());
        }
        return response;
    }
}
