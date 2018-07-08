package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.controller.response.QueryOpenDoorHistoryResp;
import com.mutong.smartlock.dao.entity.OpenDoorHistoryInfo;
import com.mutong.smartlock.dao.jpa.OpenDoorHistoryDao;
import com.mutong.smartlock.service.GetOpenDoorHistoryService;
import com.mutong.smartlock.service.common.OpenDoorHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetOpenDoorHistorySpi implements GetOpenDoorHistoryService
{
    private Logger logger = LoggerFactory.getLogger(GetOpenDoorHistorySpi.class);

    @Autowired
    private OpenDoorHistoryDao openDoorHistoryDao;

    @Override
    public QueryOpenDoorHistoryResp findOpenDoorHistory(String deviceNum)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter findOpenDoorHistory() funtion.");
        }

        QueryOpenDoorHistoryResp resp = new QueryOpenDoorHistoryResp();
        Result result = new Result();

        resp.setResult(result);

        try
        {
            List<OpenDoorHistoryInfo> openDoorHistoryInfos = openDoorHistoryDao.findByDeviceNum(deviceNum);
            if(openDoorHistoryInfos != null)
            {
                List<OpenDoorHistory> openDoorHistories = new ArrayList<OpenDoorHistory>();
                for (OpenDoorHistoryInfo openDoorHistoryInfo : openDoorHistoryInfos)
                {
                    OpenDoorHistory openDoorHistory = new OpenDoorHistory();

                    openDoorHistory.setUserName(openDoorHistoryInfo.getUserName());
                    openDoorHistory.setOpenTime(openDoorHistoryInfo.getOpenTime());

                    openDoorHistories.add(openDoorHistory);
                }
                resp.setOpenDoorHistories(openDoorHistories.toArray(new OpenDoorHistory[openDoorHistories.size()]));
            }
        }
        catch (Exception e)
        {
            result.setRetcode(ErrorCode.MYSQL_ERROR);
            result.setRetmsg("query mysql error!");
        }
        return resp;
    }
}
