package com.leo.ad.codriver.common.service;

import java.util.List;

import com.leo.ad.codriver.common.DwTaskTypeConstant;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;

public interface DwTaskRecordService {

    DwTaskRecord getLastTaskRecord(Integer dates, DwTaskTypeConstant type);

    List<DwTaskRecord> queryProcessTaskRecord(Integer dates, DwTaskTypeConstant type);

    DwTaskRecord save(DwTaskRecord taskRecord);

    void update(DwTaskRecord taskRecord);

    DwTaskRecord getLastTaskRecord(Integer dates, String tableName);

    DwTaskRecord getOneTaskRecord();
}
