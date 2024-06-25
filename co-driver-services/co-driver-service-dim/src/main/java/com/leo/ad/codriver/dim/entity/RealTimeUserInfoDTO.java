package com.leo.ad.codriver.dim.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RealTimeUserInfoDTO {

    private Long userId;
    private String gaid;
    private String aid;
    private String pkg;
    private String country;
    private String version;
    private String minVersion;
    private Integer dates;
    private Date registerTime;
    private Integer lastLoginDates;
    private Date lastLoginTime;

}
