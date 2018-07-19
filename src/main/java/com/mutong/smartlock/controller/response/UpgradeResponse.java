package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.common.Upgrade;

public class UpgradeResponse
{
    private Result result;

    private Upgrade upgrade;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }
}
