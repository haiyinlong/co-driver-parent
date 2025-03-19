package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_register")
public class DwdUserRegister implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private Long userId;
    private String pkg;
    private String country;
    private String version;
    private Date createTime;
    @TableField(exist = false)
    private String userSource;

    public boolean isInvestedUser() {
        return this.isFacebook() || this.isMintegral() || this.isGoogle();
    }

    public boolean isFacebook() {
        return "Facebook".equalsIgnoreCase(userSource);
    }

    public boolean isMintegral() {
        return "Mintegral".equalsIgnoreCase(userSource);
    }

    public boolean isGoogle() {
        return "Google".equalsIgnoreCase(userSource);
    }
}
