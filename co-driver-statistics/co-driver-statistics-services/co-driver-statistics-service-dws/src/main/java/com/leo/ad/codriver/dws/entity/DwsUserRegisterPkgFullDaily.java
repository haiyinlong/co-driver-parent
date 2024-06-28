package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dws_user_register_pkg_full_daily")
public class DwsUserRegisterPkgFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private Long userNum;
}
