package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.*;
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
import com.leo.ad.codriver.dws.service.QueryAdConversionEvent;
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
public class DwsDailyPkgVerAdConversionEventServiceImpl extends QueryAdConversionEvent implements DwsService {
    private final DwdUserEventMapper dwdUserEventMapper;
    private final DwsDailyPkgVerAdConversionEventMapper dwsDailyPkgVerAdConversionEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerAdConversionEventServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 分模块逐个处理
        DwCountDTO recordCount = dwdUserEventMapper.getCountByDate(dates, AD_EVENT_LIST);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwsDailyPkgVerAdConversionEventServiceImpl {} 没有需要同步的数据", recordCount);
            return;
        }
        dwsDailyPkgVerAdConversionEventMapper.deleteByDate(dates);
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserEventWithRegisterDateDTO> userEvents;
        Map<String, Set<Long>> eventUserIdMap = new HashMap<>();
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
                    versionMap.forEach((version, versionList) -> {
                        Map<Integer, List<DwdUserEventWithRegisterDateDTO>> registerDateMap = versionList.stream()
                            .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getRegisterDate));
                        // 用户注册日期
                        registerDateMap.forEach((key, registerDateList) -> {
                            Map<String, List<DwdUserEventWithRegisterDateDTO>> eventMap = registerDateList.stream()
                                .collect(Collectors.groupingBy(DwdUserEventWithRegisterDateDTO::getEventId));

                            eventMap.forEach((eventId, eventUserEventList) -> {
                                List<Long> userIds = eventUserEventList.stream()
                                    .map(DwdUserEventWithRegisterDateDTO::getUserId).toList();
                                int eventCount = eventUserEventList.size();
                                String pkgSourceKey = eventUserEventList.get(0).getPkgVersionKey();
                                if (Objects.equals(dates, eventUserEventList.get(0).getRegisterDate())) {
                                    DwsDailyPkgVerAdConversionEvent newUserAdConversionEvent =
                                        dwsDailyPkgVerNewUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                            DwsDailyPkgVerAdConversionEvent.ofNewUserType(dates, pkg, version));

                                    String mapKey = pkg + version + "new" + eventId;
                                    long userCount = getUserCount(eventUserIdMap, mapKey, userIds);
                                    updateEventValue(newUserAdConversionEvent, eventId, userCount, eventCount);
                                    dwsDailyPkgVerNewUserAdConversionEventMap.put(pkgSourceKey,
                                        newUserAdConversionEvent);
                                }
                                DwsDailyPkgVerAdConversionEvent userAdConversionEvent =
                                    dwsDailyPkgVerActiveUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                        DwsDailyPkgVerAdConversionEvent.ofActiveUserType(dates, pkg, version));

                                String mapKey = pkg + version + "active" + eventId;
                                long userCount = getUserCount(eventUserIdMap, mapKey, userIds);

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

    private long getUserCount(Map<String, Set<Long>> eventUserIdMap, String mapKey, List<Long> userIds) {
        Set<Long> eventUserIdList = eventUserIdMap.getOrDefault(mapKey, new HashSet<>());
        eventUserIdList.addAll(userIds);
        eventUserIdMap.put(mapKey, eventUserIdList);
        return eventUserIdList.size();
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
