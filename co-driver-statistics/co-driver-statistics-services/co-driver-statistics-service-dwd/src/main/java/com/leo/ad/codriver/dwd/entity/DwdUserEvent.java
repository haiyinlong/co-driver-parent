package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.ods.entity.OdsEventReport;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_event")
public class DwdUserEvent implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long userId;
    private String eventId;
    private String eventMsg;
    private String version;
    private String pkg;
    private Date createTime;

    public DwdUserEvent() {
        this.createTime = new Date();
    }

    public static DwdUserEvent of(OdsEventReport odsEventReport) {
        if (odsEventReport != null) {
            DwdUserEvent dwdUserEvent = new DwdUserEvent();
            dwdUserEvent.setDates(odsEventReport.getDates());
            dwdUserEvent.setUserId(Long.valueOf(odsEventReport.getUserId()));
            dwdUserEvent.setEventId(odsEventReport.getEventId());
            dwdUserEvent.setEventMsg(odsEventReport.getEventMsg());
            if (!ObjectUtils.isEmpty(odsEventReport.getEventMsg())) {
                JSONObject eventMsgJson = JSONObject.parseObject(odsEventReport.getEventMsg());
                if (eventMsgJson.containsKey("pvn")) {
                    dwdUserEvent.setVersion(eventMsgJson.getString("pvn"));
                }
            }
            dwdUserEvent.setPkg(odsEventReport.getPkg());
            return dwdUserEvent;
        }
        return null;
    }

    public String getPkgVersionKey() {
        return pkg + "_" + version;
    }
}
