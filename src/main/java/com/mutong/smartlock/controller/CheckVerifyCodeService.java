package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.CheckVerifyCodeRequest;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.response.CheckVerifyCodeResponse;
import com.mutong.smartlock.controller.response.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CheckVerifyCodeService
{
    CheckVerifyCodeResponse checkVerifyCode(@RequestBody @Valid CheckVerifyCodeRequest request);
}
