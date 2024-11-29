package com.leo.ad.codriver.common.service;

import java.util.List;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;

public interface DwTaskRecordService {

    DwTaskRecord getOetaGameRecordLastTaskRecord(Integer dates);

    List<DwTaskRecord> queryOetaGameRecordTaskRecordOfProcess(Integer dates);

    DwTaskRecord add(DwTaskRecord taskRecord);

    void update(DwTaskRecord taskRecord);

    DwTaskRecord getLastTaskRecord(Integer dates, String tableName);

    DwTaskRecord getOneTaskRecord();
}
