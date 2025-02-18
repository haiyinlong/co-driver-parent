package com.leo.ad.codriver.dws.service.impl.pkg.ver;

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
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithRegisterDateDTO;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerAdConversionEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAdConversionEvent;
import com.leo.ad.codriver.dws.event.DwsDailyAdConversionEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgVerAdConversionEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:38
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerAdConversionEventServiceImpl implements DwsService {
    private static final String AD_CLICK_EVENT = "ad_click";
    private static final String AD_SHOW_EVENT = "ad_show";
    private static final List<String> AD_EVENT_LIST = List.of(AD_CLICK_EVENT, AD_SHOW_EVENT);
    private final DwdUserEventMapper dwdUserEventMapper;
    private final DwsDailyPkgVerAdConversionEventMapper dwsDailyPkgVerAdConversionEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerAdConversionEventServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 分模块逐个处理
        dwsDailyPkgVerAdConversionEventMapper.deleteByDate(dates);
        DwCountDTO recordCount = dwdUserEventMapper.getCountByDate(dates, AD_EVENT_LIST);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwsDailyPkgVerAdConversionEventServiceImpl {} 没有需要同步的数据", recordCount);
            return;
        }
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserEventWithRegisterDateDTO> userEvents;
        Map<String, DwsDailyPkgVerAdConversionEvent> dwsDailyPkgVerNewUserAdConversionEventMap = new HashMap<>();
        Map<String, DwsDailyPkgVerAdConversionEvent> dwsDailyPkgVerActiveUserAdConversionEventMap = new HashMap<>();
        for (int i = 1; i <= loopNum; i++) {
            startId = recordCount.loopStartId(i);
            endId = recordCount.loopEndId(i);
            userEvents = dwdUserEventMapper.queryWithRegisterDateByInterval(dates, AD_EVENT_LIST, startId, endId);
            if (CollectionUtils.isEmpty(userEvents)) {
                continue;
            }
            // 进行包、用户注册日期进行统计
            userEvents.stream().collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getPkg))
                .forEach((pkg, pkgList) -> {
                    Map<String, List<DwdUserEventWithRegisterDateDTO>> versionMap =
                        pkgList.stream().collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getVersion));
                    // 版本
                    versionMap.forEach((version, sourceList) -> {
                        Map<Integer, List<DwdUserEventWithRegisterDateDTO>> registerDateMap = sourceList.stream()
                            .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getRegisterDates));
                        // 用户注册日期
                        registerDateMap.forEach((key, registerDateList) -> {
                            Map<String, List<DwdUserEventWithRegisterDateDTO>> eventMap = registerDateList.stream()
                                .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getEventId));

                            eventMap.forEach((eventId, eventUserEventList) -> {
                                // 计算用户数量，点击数量
                                long userCount = eventUserEventList.stream()
                                    .map(DwdUserEventWithRegisterDateDTO::getUserId).distinct().count();
                                int eventCount = eventUserEventList.size();
                                String pkgSourceKey = eventUserEventList.get(0).getPkgVersionKey();
                                if (Objects.equals(dates, eventUserEventList.get(0).getRegisterDates())) {
                                    DwsDailyPkgVerAdConversionEvent newUserAdConversionEvent =
                                        dwsDailyPkgVerNewUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                            DwsDailyPkgVerAdConversionEvent.ofNewUserType(dates, pkg, version));
                                    updateEventValue(newUserAdConversionEvent, eventId, userCount, eventCount);
                                    dwsDailyPkgVerNewUserAdConversionEventMap.put(pkgSourceKey,
                                        newUserAdConversionEvent);
                                }
                                DwsDailyPkgVerAdConversionEvent userAdConversionEvent =
                                    dwsDailyPkgVerActiveUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                        DwsDailyPkgVerAdConversionEvent.ofActiveUserType(dates, pkg, version));
                                updateEventValue(userAdConversionEvent, eventId, userCount, eventCount);
                                dwsDailyPkgVerActiveUserAdConversionEventMap.put(pkgSourceKey, userAdConversionEvent);
                            });
                        });
                    });
                });

        }
        // 插入数据
        dwsDailyPkgVerNewUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgVerAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        dwsDailyPkgVerActiveUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgVerAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        applicationEventPublisher.publishEvent(new DwsDailyAdConversionEventUpdateDwEvent(this, dates));

    }

    private void updateEventValue(DwsDailyPkgVerAdConversionEvent userAdConversionEvent, String eventId, long userCount,
        int eventCount) {
        if (AD_CLICK_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdClick(userCount, eventCount);
        } else if (AD_SHOW_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdShow(userCount, eventCount);
        }
    }
}
