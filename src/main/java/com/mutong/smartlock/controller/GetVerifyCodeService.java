package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.GetVerifyCodeRequest;
import com.mutong.smartlock.controller.response.GetVerifyCodeResponse;

public interface GetVerifyCodeService
{
    GetVerifyCodeResponse getVerifyCode(GetVerifyCodeRequest request);
}
