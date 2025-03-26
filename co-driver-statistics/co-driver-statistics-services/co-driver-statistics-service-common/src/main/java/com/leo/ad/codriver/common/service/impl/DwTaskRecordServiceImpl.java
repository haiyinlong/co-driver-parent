package com.leo.ad.codriver.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.DwTaskTypeConstant;
import com.leo.ad.codriver.common.dao.DwTaskRecordMapper;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dw_task_record(数据同步记录,针对把的执行记录)】的数据库操作Service实现
 * @createDate 2024-11-11 15:21:49
 */
@Service
@RequiredArgsConstructor
public class DwTaskRecordServiceImpl implements DwTaskRecordService {

    private final DwTaskRecordMapper dwTaskRecordMapper;

    @Override
    public DwTaskRecord getLastTaskRecord(Integer dates, DwTaskTypeConstant type) {
        return dwTaskRecordMapper.getLastTaskRecord(dates, type.getType());
    }

    @Override
    public List<DwTaskRecord> queryProcessTaskRecord(Integer dates, DwTaskTypeConstant type) {
        return dwTaskRecordMapper.queryTaskRecord(dates, type.getType(), 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DwTaskRecord save(DwTaskRecord taskRecord) {
        dwTaskRecordMapper.insert(taskRecord);
        return taskRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DwTaskRecord taskRecord) {
        dwTaskRecordMapper.updateById(taskRecord);
    }

    @Override
    public DwTaskRecord getLastTaskRecord(Integer dates, String tableName) {
        return dwTaskRecordMapper.getLastTaskRecord(dates, tableName);
    }

    @Override
    public DwTaskRecord getOneTaskRecord(long indexTaskRecordId) {
        return dwTaskRecordMapper.queryFirstTaskRecordNotDone(indexTaskRecordId);
    }
}
