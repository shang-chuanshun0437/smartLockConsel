package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.response.UpgradeResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UpgradeService
{
    UpgradeResponse upgrade();

    String downLoadApk(HttpServletRequest request, HttpServletResponse response);
}
