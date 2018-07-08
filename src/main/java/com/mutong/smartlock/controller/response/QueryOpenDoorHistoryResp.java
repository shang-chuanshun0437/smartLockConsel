package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.service.common.OpenDoorHistory;

public class QueryOpenDoorHistoryResp
{
    private Result result;

    private OpenDoorHistory[] openDoorHistories;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public OpenDoorHistory[] getOpenDoorHistories() {
        return openDoorHistories;
    }

    public void setOpenDoorHistories(OpenDoorHistory[] openDoorHistories) {
        this.openDoorHistories = openDoorHistories;
    }
}
