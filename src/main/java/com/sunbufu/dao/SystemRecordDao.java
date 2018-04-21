package com.sunbufu.dao;

import com.sunbufu.entity.SystemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRecordDao extends JpaRepository<SystemRecord, Integer> {
}
