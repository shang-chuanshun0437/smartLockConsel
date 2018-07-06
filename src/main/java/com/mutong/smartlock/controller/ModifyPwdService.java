package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.request.ModifyPwdRequest;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.controller.response.ModifyPwdResponse;

public interface ModifyPwdService
{
    ModifyPwdResponse modifyPwd(ModifyPwdRequest request);
}
