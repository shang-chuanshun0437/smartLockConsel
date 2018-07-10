package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.OpenDoorHistoryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OpenDoorHistoryDao extends JpaRepository<OpenDoorHistoryInfo,Integer>,JpaSpecificationExecutor<OpenDoorHistoryInfo>
{

}
