package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;

public interface AddUserService
{
    AddUserResponse addUser(AddUserRequest request);
}
