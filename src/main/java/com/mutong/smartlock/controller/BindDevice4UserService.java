package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.BindDevice4UserRequest;
import com.mutong.smartlock.controller.response.BindDevice4UserResponse;

public interface BindDevice4UserService
{
    BindDevice4UserResponse bindDevice4User(BindDevice4UserRequest request);
}
