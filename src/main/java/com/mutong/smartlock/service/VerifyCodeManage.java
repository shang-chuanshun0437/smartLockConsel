package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.*;
import com.mutong.smartlock.controller.response.*;

public interface VerifyCodeManage
{
    GetVerifyCodeResponse getVerifyCode(GetVerifyCodeRequest request);

    CheckVerifyCodeResponse checkVerifyCode(CheckVerifyCodeRequest request);
}
