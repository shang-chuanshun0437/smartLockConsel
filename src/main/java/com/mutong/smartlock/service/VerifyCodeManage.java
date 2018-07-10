package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.request.ModifyPwdRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.controller.response.ModifyPwdResponse;

public interface VerifyCodeManage
{
    GetVerifyCodeResponse getVerifyCode(GetVerifyCodeRequest request);
}
