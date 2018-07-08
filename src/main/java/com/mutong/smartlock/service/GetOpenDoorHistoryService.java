package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.response.QueryOpenDoorHistoryResp;

public interface GetOpenDoorHistoryService
{
    QueryOpenDoorHistoryResp findOpenDoorHistory(String deviceNum);
}
