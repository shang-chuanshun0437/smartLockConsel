package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.GetVerifyCodeService;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
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
public class GetVerifyCodeServiceSpi implements GetVerifyCodeService
{
    private Logger logger = LoggerFactory.getLogger(GetVerifyCodeServiceSpi.class);
    @Autowired
    private VerifyCodeManagerSpi verifyCodeManagerSpi;

    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.POST)
    @Override
    public GetVerifyCodeResponse getVerifyCode(@RequestBody @Valid GetVerifyCodeRequest request)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter getVerifyCode():{}",request.toString());
        }
        GetVerifyCodeResponse response = new GetVerifyCodeResponse();
        Result result = new Result();
        response.setResult(result);
        try
        {
            response = verifyCodeManagerSpi.getVerifyCode(request);
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
