package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dwd_user_account_record")
public class DwdUserAccountRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String source;
    private Long sourceId;
    private Long userId;
    private BigDecimal amount;
    private String recordType;
    private String operation;
    private String gameName;
    private String version;
    private String pkg;
    private String country;
    private Date registerDate;
    private Integer registerDates;
    private String registerVersion;
    private Long registerDay;
    private Long registerCohortDay;
    private Date createTime;

}
