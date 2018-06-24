package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.response.LoginResponse;

public interface LoginService
{
    LoginResponse login(LoginRequest request);
}
