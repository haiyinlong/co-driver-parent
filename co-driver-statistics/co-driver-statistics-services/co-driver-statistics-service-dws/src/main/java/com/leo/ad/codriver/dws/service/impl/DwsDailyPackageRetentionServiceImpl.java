// package com.leo.ad.codriver.dws.service.impl;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
// import com.leo.ad.codriver.dws.dao.DwsDailyPackageRetentionMapper;
// import com.leo.ad.codriver.dws.service.DwsTestService;
// import com.leo.ad.codriver.starter.redis.annotation.Lock;
//
// import lombok.AllArgsConstructor;
//
/// **
// * DwsServiceImpl
// *
// * @author HaiYinLong
// * @version 2024/04/15 18:34
// **/
// @Service
// @AllArgsConstructor
// public class DwsDailyPackageRetentionServiceImpl implements DwsTestService {
//
// private final DwsDailyPackageRetentionMapper dwsDailyPackageRetentionMapper;
//
// @Override
// @ShowExecuteTime(name = "dwsDailyPackageRetention syncData")
// @Transactional(rollbackFor = Exception.class)
// @Lock(paramName = "#dates")
// public void syncData(Integer dates) {
// dwsDailyPackageRetentionMapper.deleteByDates(dates);
// dwsDailyPackageRetentionMapper.syncData(dates);
// }
// }
