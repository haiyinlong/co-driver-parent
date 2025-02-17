package com.leo.ad.codriver.dwd.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dwd.dao.DwdUserEventMapper;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserEvent;
import com.leo.ad.codriver.dwd.service.DwdStreamService;

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
public class DwdUserEventFormEventReportServiceImpl implements DwdStreamService {
    private final DwdUserEventMapper dwdUserEventMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncChangeData(DataChangeDTO dataChangeDTO) {
        if (ObjectUtils.isEmpty(dataChangeDTO.getDates())) {
            return true;
        }
        JSONObject odsUserChangeJson = JSONObject.parseObject(dataChangeDTO.getMsg());
        long userId = Long.parseLong(odsUserChangeJson.getString("user_id"));
        String pkg = odsUserChangeJson.getString("pkg");
        String eventId = odsUserChangeJson.getString("event_id");
        String eventMsg = odsUserChangeJson.getString("event_msg");
        String pvn = "";
        if (!ObjectUtils.isEmpty(eventMsg)) {
            pvn = JSONObject.parseObject(eventMsg).getString("pvn");
        }
        // 解析msg 封装对象
        DwdUserEvent dwdUserEvent = new DwdUserEvent();
        dwdUserEvent.setId(dataChangeDTO.getSourceId());
        dwdUserEvent.setDates(dataChangeDTO.getDates());
        dwdUserEvent.setUserId(userId);
        dwdUserEvent.setPkg(pkg);
        dwdUserEvent.setVersion(pvn);
        dwdUserEvent.setEventId(eventId);
        dwdUserEvent.setEventMsg(eventMsg);
        DwdUserEvent dbDwdUserEvent = dwdUserEventMapper.selectById(dwdUserEvent.getId());
        if (ObjectUtils.isEmpty(dbDwdUserEvent)) {
            dwdUserEventMapper.insert(dwdUserEvent);
        } else {
            dwdUserEventMapper.updateById(dwdUserEvent);
        }
        return true;
    }
}
