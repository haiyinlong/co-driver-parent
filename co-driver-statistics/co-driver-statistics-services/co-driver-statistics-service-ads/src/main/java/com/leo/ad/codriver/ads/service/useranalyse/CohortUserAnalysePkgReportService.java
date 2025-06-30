package com.leo.ad.codriver.ads.service.useranalyse;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.ads.dao.AdsDailyOetaBaseReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * CohortUserAnalysePkgReportService
 *
 * @author HaiYinLong
 * @version 2025/06/27 18:35
 **/
@Service
@RequiredArgsConstructor
public class CohortUserAnalysePkgReportService implements AdsService {
    private final AdsDailyOetaBaseReportMapper adsDailyOetaBaseReportMapper;
    private final DwBatchMapper<AdsDailyOetaBaseReport, AdsDailyOetaBaseReportMapper> batchMapper;

    @Override
    public void syncData(Integer dates) {

    }
}
