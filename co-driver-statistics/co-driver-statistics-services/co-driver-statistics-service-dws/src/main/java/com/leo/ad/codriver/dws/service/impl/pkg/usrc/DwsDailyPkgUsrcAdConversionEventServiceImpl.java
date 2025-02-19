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
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithUserSourceDTO;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcAdConversionEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAdConversionEvent;
import com.leo.ad.codriver.dws.event.DwsDailyAdConversionEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.dws.service.QueryAdConversionEvent;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgUserAdConversionEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:38
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcAdConversionEventServiceImpl extends QueryAdConversionEvent implements DwsService {
    private final DwdUserEventMapper dwdUserEventMapper;
    private final DwsDailyPkgUsrcAdConversionEventMapper dwsDailyPkgUsrcAdConversionEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgUserAdConversionEventServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 分模块逐个处理
        dwsDailyPkgUsrcAdConversionEventMapper.deleteByDate(dates);
        DwCountDTO recordCount = dwdUserEventMapper.getCountByDate(dates, AD_EVENT_LIST);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwsDailyPkgUserAdConversionEventServiceImpl {} 没有需要同步的数据", recordCount);
            return;
        }
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserEventWithUserSourceDTO> userEvents;
        Map<String, DwsDailyPkgUsrcAdConversionEvent> dwsDailyPkgSourceNewUserAdConversionEventMap = new HashMap<>();
        Map<String, DwsDailyPkgUsrcAdConversionEvent> dwsDailyPkgSourceActiveUserAdConversionEventMap = new HashMap<>();
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
                    Map<String, List<DwdUserEventWithUserSourceDTO>> sourceMap =
                        pkgList.stream().collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getUserSource));
                    // 用户来源
                    sourceMap.forEach((source, sourceList) -> {
                        Map<Integer, List<DwdUserEventWithUserSourceDTO>> registerDateMap = sourceList.stream()
                            .collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getRegisterDate));
                        // 用户注册日期
                        registerDateMap.forEach((key, registerDateList) -> {
                            Map<String, List<DwdUserEventWithUserSourceDTO>> eventMap = registerDateList.stream()
                                .collect(Collectors.groupingBy(DwdUserEventWithUserSourceDTO::getEventId));

                            eventMap.forEach((eventId, eventUserEventList) -> {
                                // 计算用户数量，点击数量
                                long userCount = eventUserEventList.stream()
                                    .map(DwdUserEventWithUserSourceDTO::getUserId).distinct().count();
                                int eventCount = eventUserEventList.size();
                                String pkgSourceKey = eventUserEventList.get(0).getPkgSourceKey();
                                if (Objects.equals(dates, eventUserEventList.get(0).getRegisterDate())) {
                                    DwsDailyPkgUsrcAdConversionEvent newUserAdConversionEvent =
                                        dwsDailyPkgSourceNewUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                            DwsDailyPkgUsrcAdConversionEvent.ofNewUserType(dates, pkg, source));
                                    updateEventValue(newUserAdConversionEvent, eventId, userCount, eventCount);
                                    dwsDailyPkgSourceNewUserAdConversionEventMap.put(pkgSourceKey,
                                        newUserAdConversionEvent);
                                }
                                DwsDailyPkgUsrcAdConversionEvent userAdConversionEvent =
                                    dwsDailyPkgSourceActiveUserAdConversionEventMap.getOrDefault(pkgSourceKey,
                                        DwsDailyPkgUsrcAdConversionEvent.ofActiveUserType(dates, pkg, source));
                                updateEventValue(userAdConversionEvent, eventId, userCount, eventCount);
                                dwsDailyPkgSourceActiveUserAdConversionEventMap.put(pkgSourceKey,
                                    userAdConversionEvent);
                            });
                        });
                    });
                });

        }
        // 插入数据
        dwsDailyPkgSourceNewUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgUsrcAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        dwsDailyPkgSourceActiveUserAdConversionEventMap.forEach((pkg, dwsDailyPkgAdConversionEvent) -> {
            dwsDailyPkgUsrcAdConversionEventMapper.insert(dwsDailyPkgAdConversionEvent);
        });
        applicationEventPublisher.publishEvent(new DwsDailyAdConversionEventUpdateDwEvent(this, dates));
    }

    private void updateEventValue(DwsDailyPkgUsrcAdConversionEvent userAdConversionEvent, String eventId,
        long userCount, int eventCount) {
        if (AD_CLICK_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdClick(userCount, eventCount);
        } else if (AD_SHOW_EVENT.equalsIgnoreCase(eventId)) {
            userAdConversionEvent.updateAdShow(userCount, eventCount);
        }
    }
}
