package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dwd_user_register")
public class DwdUserRegister {

    private Long id;
    private Long dates;
    private Long userId;
    private String pkg;
    private String country;
    private String version;
    private Date createTime;

}
