package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.*;
import com.mutong.smartlock.controller.response.*;

public interface UserManage
{
    AddUserResponse addUser(AddUserRequest request);

    DeleteUserResponse deleteUser(DeleteUserRequest request);

    ModifyUserResponse modifyUser(ModifyUserRequest request);

    LoginResponse login(LoginRequest request);

    ModifyPwdResponse modifyPwd(ModifyPwdRequest request);

}
