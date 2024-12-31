package com.leo.ad.codriver.dim.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dim_user_info")
public class DimUserInfo {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String gaid;
    private String aid;
    private String pkg;
    private String country;
    private String version;
    private Integer dates;
    private Date registerTime;
    private String lastVersion;
    private Integer lastLoginDates;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;

    public static DimUserInfo createUserInfo(RealTimeUserInfoDTO realTimeUserInfo) {
        DimUserInfo dimUserInfo = new DimUserInfo();
        dimUserInfo.setUserId(realTimeUserInfo.getUserId());
        dimUserInfo.setGaid(realTimeUserInfo.getGaid());
        dimUserInfo.setAid(realTimeUserInfo.getAid());
        dimUserInfo.setPkg(realTimeUserInfo.getPkg());
        dimUserInfo.setCountry(realTimeUserInfo.getCountry());
        dimUserInfo.setVersion(realTimeUserInfo.getMinVersion());
        dimUserInfo.setDates(realTimeUserInfo.getDates());
        dimUserInfo.setRegisterTime(realTimeUserInfo.getRegisterTime());
        dimUserInfo.setLastVersion(realTimeUserInfo.getVersion());
        dimUserInfo.setLastLoginDates(realTimeUserInfo.getLastLoginDates());
        dimUserInfo.setLastLoginTime(realTimeUserInfo.getLastLoginTime());
        dimUserInfo.initCreateTime();
        return dimUserInfo;
    }

    public void updateUserInfo(RealTimeUserInfoDTO realTimeUserInfo) {
        this.setVersion(realTimeUserInfo.getMinVersion());
        this.setLastVersion(realTimeUserInfo.getVersion());
        this.setLastLoginDates(realTimeUserInfo.getLastLoginDates());
        this.setLastLoginTime(realTimeUserInfo.getLastLoginTime());
        this.modifyUpdateTime();
    }

    public void initCreateTime() {
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }

    public void modifyUpdateTime() {
        this.updateTime = new Date();
    }

}
