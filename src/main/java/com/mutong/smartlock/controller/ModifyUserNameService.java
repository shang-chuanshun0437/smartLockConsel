package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.ModifyUserNameRequest;
import com.mutong.smartlock.controller.response.ModifyUserNameResponse;

public interface ModifyUserNameService
{
    ModifyUserNameResponse modifyUserName(ModifyUserNameRequest request);
}
