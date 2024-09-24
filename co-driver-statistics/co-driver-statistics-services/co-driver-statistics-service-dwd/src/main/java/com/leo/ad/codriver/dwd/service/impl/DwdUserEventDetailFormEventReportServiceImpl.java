package com.leo.ad.codriver.dwd.service.impl;

import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.dwd.dao.DwdUserEventDetailMapper;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserEventDetail;
import com.leo.ad.codriver.dwd.service.DwdStreamService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserEventDetailFormEventReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserEventDetailFormEventReportServiceImpl implements DwdStreamService {
    public static final String ODS_EVENT_REPORT = "ods_event_report";
    private final DwdUserEventDetailMapper dwdUserEventDetailMapper;
    private final DwBatchMapper<DwdUserEventDetail, DwdUserEventDetailMapper> batchMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncChangeData(DataChangeDTO dataChangeDTO) {
        DwdUserEventDetail dwdUserEventDetail =
            dwdUserEventDetailMapper.getStatisticsEventReport(ODS_EVENT_REPORT, dataChangeDTO.getSourceId());
        if (ObjectUtils.isEmpty(dwdUserEventDetail)) {
            return true;
        }
        batchMapper.batchInsert(Collections.singletonList(dwdUserEventDetail), DwdUserEventDetailMapper.class);
        return true;
    }
}
