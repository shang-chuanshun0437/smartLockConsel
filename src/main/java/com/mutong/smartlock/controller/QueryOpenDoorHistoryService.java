package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.QueryOpenDoorHistoryReq;
import com.mutong.smartlock.controller.response.QueryOpenDoorHistoryResp;

public interface QueryOpenDoorHistoryService
{
    QueryOpenDoorHistoryResp queryOpenDoorHistory(QueryOpenDoorHistoryReq request);
}
