package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dws_pkg_retention_full_daily")
public class DwsPkgRetentionFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private Integer registerDates;
    private Integer registerDay;
    private Long retentionUserNum;

}
