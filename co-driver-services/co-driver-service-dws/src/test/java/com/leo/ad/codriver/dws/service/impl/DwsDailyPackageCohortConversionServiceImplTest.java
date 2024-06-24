package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.dws.dao.DwsDailyCohortConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortConversion;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.*;

class DwsDailyPackageCohortConversionServiceImplTest {
    @Mock
    private DwsDailyCohortConversionMapper dwsDailyCohortConversionMapper;

    @Mock
    private DwBatchMapper<DwsDailyCohortConversion, DwsDailyCohortConversionMapper> dwBatchMapper;

    @InjectMocks
    private DwsDailyPackageCohortConversionServiceImpl dwsDailyPackageCohortConversionServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化Mockito注解
    }

    @Test
    public void testSyncData() {
        Integer dates = 20220101;

        // 设置模拟行为
        doNothing().when(dwsDailyCohortConversionMapper).deleteByDates(dates);
        when(dwsDailyCohortConversionMapper.statisticsCohortConversion(dates))
                .thenReturn(Collections.emptyList());

        // 调用待测试方法
        dwsDailyPackageCohortConversionServiceImpl.syncData(dates);

        // 验证交互
        verify(dwsDailyCohortConversionMapper).deleteByDates(dates);
        verify(dwsDailyCohortConversionMapper).statisticsCohortConversion(dates);
        verify(dwBatchMapper).batchInsert(eq(Collections.emptyList()), any());
    }
}
