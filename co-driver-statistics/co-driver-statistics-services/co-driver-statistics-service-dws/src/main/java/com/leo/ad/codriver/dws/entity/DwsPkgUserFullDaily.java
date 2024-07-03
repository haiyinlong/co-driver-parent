package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

/**
 * DwsPkgGameFullDaily
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:56
 **/
@Data
@TableName("dws_pkg_user_full_daily")
public class DwsPkgUserFullDaily implements BaseEntity {
    private Long id;
    private Integer dates;
    private String pkg;
    private Integer loginUserNum;
    private Integer registerUserNum;
}
