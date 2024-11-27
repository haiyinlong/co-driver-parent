package com.leo.ad.codriver.dws.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_register
 */
@TableName(value = "dws_daily_pkg_ver_usrc_register")
@Data
public class DwsDailyPkgVerUsrcRegister implements BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 日期
     */
    private Long dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 注册用户数
     */
    private Integer userNum;

    private Integer verUserNum;
    private Integer totalUserNum;
    private Integer totalInvestedUserNum;
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> users;

    public DwsDailyPkgVerUsrcRegister() {
        this.userNum = 0;
        this.totalUserNum = 0;
        this.totalInvestedUserNum = 0;
        this.verUserNum = 0;
        this.createTime = new Date();
    }

    public static DwsDailyPkgVerUsrcRegister of(Long dates, String pkg, String version, String userSource) {
        DwsDailyPkgVerUsrcRegister dwsDailyPkgVerUsrcRegister = new DwsDailyPkgVerUsrcRegister();
        dwsDailyPkgVerUsrcRegister.setDates(dates);
        dwsDailyPkgVerUsrcRegister.setPkg(pkg);
        dwsDailyPkgVerUsrcRegister.setVersion(version);
        dwsDailyPkgVerUsrcRegister.setUserSource(userSource);
        return dwsDailyPkgVerUsrcRegister;
    }

    public void calculate(DwdUserRegister dwdUserRegister) {
        if (CollectionUtils.isEmpty(this.users)) {
            this.users = new HashSet<>();
        }
        this.users.add(dwdUserRegister.getUserId());
        this.userNum = this.users.size();
    }

    public void setVerTotal(Integer varUserNum) {
        this.verUserNum = varUserNum;
    }

    public void setTotal(Integer totalUserNum, Integer totalInvestedUserNum) {
        this.totalUserNum = totalUserNum;
        this.totalInvestedUserNum = totalInvestedUserNum;
    }
}
