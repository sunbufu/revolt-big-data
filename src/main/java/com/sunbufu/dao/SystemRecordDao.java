package com.sunbufu.dao;

import com.sunbufu.po.SystemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRecordDao extends JpaRepository<SystemRecord, Integer> {
}
