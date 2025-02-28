package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserEventMapper;
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithUserSourceDTO;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcAdConversionEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdConversionEvent;
import com.leo.ad.codriver.dws.event.DwsDailyAdConversionEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.dws.service.QueryAdConversionEvent;
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
public class DwsDailyPkgVerUsrcAdConversionEventServiceImpl extends QueryAdConversionEvent implements DwsService {
    private final DwdUserEventMapper dwdUserEventMapper;
    private final DwsDailyPkgVerUsrcAdConversionEventMapper dwsDailyPkgVerUsrcAdConversionEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcAdConversionEventServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 分模块逐个处理
        DwCountDTO recordCount = dwdUserEventMapper.getCountByDate(dates, AD_EVENT_LIST);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwsDailyPkgVerUsrcAdConversionEventServiceImpl {} 没有需要同步的数据", recordCount);
            return;
        }
        dwsDailyPkgVerUsrcAdConversionEventMapper.deleteByDate(dates);
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserEventWithUserSourceDTO> userEvents;
        Map<String, Set<Long>> eventUserIdMap = new HashMap<>();
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
            userEvents.stream().collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getPkg))
                .forEach((pkg, pkgList) -> {
                    // 版本
                    Map<String, List<DwdUserEventWithUserSourceDTO>> versionMap =
                        pkgList.stream().collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getVersion));

                    versionMap.forEach((version, vesrsionList) -> {
                        Map<String, List<DwdUserEventWithUserSourceDTO>> sourceMap = vesrsionList.stream()
                            .collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getUserSource));
                        // 用户来源
                        sourceMap.forEach((source, sourceList) -> {
                            Map<Integer, List<DwdUserEventWithUserSourceDTO>> registerDateMap = sourceList.stream()
                                .collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getRegisterDate));
                            // 用户注册日期
                            registerDateMap.forEach((key, registerDateList) -> {
                                Map<String, List<DwdUserEventWithUserSourceDTO>> eventMap = registerDateList.stream()
                                    .collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getEventId));

                                eventMap.forEach((eventId, eventUserEventList) -> {
                                    List<Long> userIds = eventUserEventList.stream()
                                        .map(DwdUserEventWithUserSourceDTO::getUserId).toList();

                                    int eventCount = eventUserEventList.size();
                                    String pkgSourceKey = eventUserEventList.get(0).getPkgVersionSourceKey();
                                    if (Objects.equals(dates, eventUserEventList.get(0).getRegisterDate())) {
                                        DwsDailyPkgVerUsrcAdConversionEvent newUserAdConversionEvent =
                                            dwsDailyPkgSourceNewUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                                DwsDailyPkgVerUsrcAdConversionEvent.ofNewUserType(dates, pkg, version,
                                                    source));
                                        String mapKey = pkg + version + source + "new" + eventId;
                                        long userCount = getUserCount(eventUserIdMap, mapKey, userIds);
                                        updateEventValue(newUserAdConversionEvent, eventId, userCount, eventCount);
                                        dwsDailyPkgSourceNewUserAdConversionEventMap.put(pkgSourceKey,
                                            newUserAdConversionEvent);
                                    }
                                    DwsDailyPkgVerUsrcAdConversionEvent userAdConversionEvent =
                                        dwsDailyPkgSourceActiveUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                            DwsDailyPkgVerUsrcAdConversionEvent.ofActiveUserType(dates, pkg, version,
                                                source));

                                    String mapKey = pkg + version + source + "active" + eventId;
                                    long userCount = getUserCount(eventUserIdMap, mapKey, userIds);

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

    private long getUserCount(Map<String, Set<Long>> eventUserIdMap, String mapKey, List<Long> userIds) {
        Set<Long> eventUserIdList = eventUserIdMap.getOrDefault(mapKey, new HashSet<>());
        eventUserIdList.addAll(userIds);
        eventUserIdMap.put(mapKey, eventUserIdList);
        return eventUserIdList.size();
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
