package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.request.DeleteUserRequest;
import com.mutong.smartlock.controller.request.LoginRequest;
import com.mutong.smartlock.controller.request.ModifyUserRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.controller.response.DeleteUserResponse;
import com.mutong.smartlock.controller.response.LoginResponse;
import com.mutong.smartlock.controller.response.ModifyUserResponse;

public interface UserManage
{
    AddUserResponse addUser(AddUserRequest request);

    DeleteUserResponse deleteUser(DeleteUserRequest request);

    ModifyUserResponse modifyUser(ModifyUserRequest request);

    LoginResponse login(LoginRequest request);

}
