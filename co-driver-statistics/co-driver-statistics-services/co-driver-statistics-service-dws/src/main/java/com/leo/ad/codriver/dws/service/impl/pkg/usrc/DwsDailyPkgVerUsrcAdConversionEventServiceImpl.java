package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserEventMapper;
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithRegisterDateSourceDTO;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcAdConversionEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdConversionEvent;
import com.leo.ad.codriver.dws.event.DwsDailyAdConversionEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgVerUsrcAdConversionEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:38
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcAdConversionEventServiceImpl implements DwsService {
    private static final String AD_CLICK_EVENT = "ad_click";
    private static final String AD_SHOW_EVENT = "ad_show";
    private static final List<String> AD_EVENT_LIST = List.of(AD_CLICK_EVENT, AD_SHOW_EVENT);
    private final DwdUserEventMapper dwdUserEventMapper;
    private final DwsDailyPkgVerUsrcAdConversionEventMapper dwsDailyPkgVerUsrcAdConversionEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcAdConversionEventServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 分模块逐个处理
        dwsDailyPkgVerUsrcAdConversionEventMapper.deleteByDate(dates);
        DwCountDTO recordCount = dwdUserEventMapper.getCountByDate(dates, AD_EVENT_LIST);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwsDailyPkgVerUsrcAdConversionEventServiceImpl {} 没有需要同步的数据", recordCount);
            return;
        }
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserEventWithRegisterDateSourceDTO> userEvents;
        Map<String, DwsDailyPkgVerUsrcAdConversionEvent> dwsDailyPkgSourceNewUserAdConversionEventMap = new HashMap<>();
        Map<String, DwsDailyPkgVerUsrcAdConversionEvent> dwsDailyPkgSourceActiveUserAdConversionEventMap =
            new HashMap<>();
        for (int i = 1; i <= loopNum; i++) {
            startId = recordCount.loopStartId(i);
            endId = recordCount.loopEndId(i);
            userEvents =
                dwdUserEventMapper.queryWithRegisterDateAndSourceByInterval(dates, AD_EVENT_LIST, startId, endId);
            if (CollectionUtils.isEmpty(userEvents)) {
                continue;
            }
            // 进行包、用户注册日期进行统计
            userEvents.stream().collect(Collectors.groupingBy(DwdUserEventWithRegisterDateSourceDTO::getPkg))
                .forEach((pkg, pkgList) -> {
                    // 版本
                    Map<String, List<DwdUserEventWithRegisterDateSourceDTO>> versionMap = pkgList.stream()
                        .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateSourceDTO::getVersion));

                    versionMap.forEach((version, vesrsionList) -> {
                        Map<String, List<DwdUserEventWithRegisterDateSourceDTO>> sourceMap = vesrsionList.stream()
                            .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateSourceDTO::getUserSource));
                        // 用户来源
                        sourceMap.forEach((source, sourceList) -> {
                            Map<Integer, List<DwdUserEventWithRegisterDateSourceDTO>> registerDateMap =
                                sourceList.stream().collect(
                                    Collectors.groupingBy(DwdUserEventWithRegisterDateSourceDTO::getRegisterDates));
                            // 用户注册日期
                            registerDateMap.forEach((key, registerDateList) -> {
                                Map<String, List<DwdUserEventWithRegisterDateSourceDTO>> eventMap =
                                    registerDateList.stream().collect(
                                        Collectors.groupingBy(DwdUserEventWithRegisterDateSourceDTO::getEventId));

                                eventMap.forEach((eventId, eventUserEventList) -> {
                                    // 计算用户数量，点击数量
                                    long userCount = eventUserEventList.stream()
                                        .map(DwdUserEventWithRegisterDateSourceDTO::getUserId).distinct().count();
                                    int eventCount = eventUserEventList.size();
                                    String pkgSourceKey = eventUserEventList.get(0).getPkgVersionSourceKey();
                                    if (Objects.equals(dates, eventUserEventList.get(0).getRegisterDates())) {
                                        DwsDailyPkgVerUsrcAdConversionEvent newUserAdConversionEvent =
                                            dwsDailyPkgSourceNewUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                                DwsDailyPkgVerUsrcAdConversionEvent.ofNewUserType(dates, pkg, version,
                                                    source));
                                        updateEventValue(newUserAdConversionEvent, eventId, userCount, eventCount);
                                        dwsDailyPkgSourceNewUserAdConversionEventMap.put(pkgSourceKey,
                                            newUserAdConversionEvent);
                                    }
                                    DwsDailyPkgVerUsrcAdConversionEvent userAdConversionEvent =
                                        dwsDailyPkgSourceActiveUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                            DwsDailyPkgVerUsrcAdConversionEvent.ofActiveUserType(dates, pkg, version,
                                                source));
                                    updateEventValue(userAdConversionEvent, eventId, userCount, eventCount);
                                    dwsDailyPkgSourceActiveUserAdConversionEventMap.put(pkgSourceKey,
                                        userAdConversionEvent);
                                });
                            });
                        });
                    });

                });

        }
        // 插入数据
        dwsDailyPkgSourceNewUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgVerUsrcAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        dwsDailyPkgSourceActiveUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgVerUsrcAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        applicationEventPublisher.publishEvent(new DwsDailyAdConversionEventUpdateDwEvent(this, dates));

    }

    private void updateEventValue(DwsDailyPkgVerUsrcAdConversionEvent userAdConversionEvent, String eventId,
        long userCount, int eventCount) {
        if (AD_CLICK_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdClick(userCount, eventCount);
        } else if (AD_SHOW_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdShow(userCount, eventCount);
        }
    }
}
