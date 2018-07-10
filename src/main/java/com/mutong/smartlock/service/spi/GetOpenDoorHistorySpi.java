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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetOpenDoorHistorySpi implements GetOpenDoorHistoryService
{
    private Logger logger = LoggerFactory.getLogger(GetOpenDoorHistorySpi.class);

    @Autowired
    private OpenDoorHistoryDao openDoorHistoryDao;

    @Override
    public QueryOpenDoorHistoryResp findOpenDoorHistory(String deviceNum,int page)
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
            //分页查询,每页查询30条数据
            Pageable pageable = new PageRequest(page, 30, Sort.Direction.DESC, "id");
            Page<OpenDoorHistoryInfo> openDoorPage = openDoorHistoryDao.findAll(new Specification<OpenDoorHistoryInfo>()
            {
                @Override
                public Predicate toPredicate(Root<OpenDoorHistoryInfo> root,
                                             CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
                {
                    return criteriaBuilder.equal(root.get("deviceNum").as(String.class), deviceNum);
                }
            },pageable);

            List<OpenDoorHistoryInfo> openDoorHistoryInfos = openDoorPage.getContent();

            if(openDoorHistoryInfos != null)
            {
                List<OpenDoorHistory> openDoorHistories = new ArrayList<OpenDoorHistory>();
                for (OpenDoorHistoryInfo openDoorHistoryInfo : openDoorHistoryInfos)
                {
                    OpenDoorHistory openDoorHistory = new OpenDoorHistory();

                    openDoorHistory.setUserName(openDoorHistoryInfo.getUserName());
                    openDoorHistory.setOpenTime(openDoorHistoryInfo.getOpenTime());
                    openDoorHistory.setPhoneType(openDoorHistoryInfo.getPhoneType());

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
