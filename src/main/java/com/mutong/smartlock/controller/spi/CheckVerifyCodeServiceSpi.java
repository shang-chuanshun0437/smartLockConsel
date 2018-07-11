package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.CheckVerifyCodeService;
import com.mutong.smartlock.controller.GetVerifyCodeService;
import com.mutong.smartlock.controller.request.CheckVerifyCodeRequest;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.response.CheckVerifyCodeResponse;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;
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
@RequestMapping("/v1/verifyCode")
public class CheckVerifyCodeServiceSpi implements CheckVerifyCodeService
{
    private Logger logger = LoggerFactory.getLogger(CheckVerifyCodeServiceSpi.class);
    @Autowired
    private VerifyCodeManagerSpi verifyCodeManagerSpi;

    @RequestMapping(value = "/checkVerifyCode",method = RequestMethod.POST)
    @Override
    public CheckVerifyCodeResponse checkVerifyCode(@RequestBody @Valid CheckVerifyCodeRequest request)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter checkVerifyCode(),{}",request.toString());
        }
        CheckVerifyCodeResponse response = new CheckVerifyCodeResponse();
        Result result = new Result();
        response.setResult(result);
        try
        {
            response = verifyCodeManagerSpi.checkVerifyCode(request);
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
