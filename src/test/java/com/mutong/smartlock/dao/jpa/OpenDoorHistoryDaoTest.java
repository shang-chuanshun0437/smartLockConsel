package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.OpenDoorHistoryInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenDoorHistoryDaoTest
{
    @Autowired
    private OpenDoorHistoryDao openDoorHistoryDao;
    @Test
    public void testPage()
    {
        //分页查询,每页查询30条数据
        Pageable pageable = new PageRequest(3, 30, Sort.Direction.DESC, "id");
        Page<OpenDoorHistoryInfo> openDoorPage = openDoorHistoryDao.findAll(new Specification<OpenDoorHistoryInfo>()
        {
            @Override
            public Predicate toPredicate(Root<OpenDoorHistoryInfo> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(root.get("deviceNum").as(String.class), "00000000000");
            }
        },pageable);

        List<OpenDoorHistoryInfo> openDoorHistoryInfos = openDoorPage.getContent();
    }

}