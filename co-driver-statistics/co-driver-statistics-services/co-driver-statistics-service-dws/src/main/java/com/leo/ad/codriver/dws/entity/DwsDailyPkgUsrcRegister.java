package com.leo.ad.codriver.dws.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_register
 */
@TableName(value = "dws_daily_pkg_usrc_register")
@Data
public class DwsDailyPkgUsrcRegister implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long dates;
    private String pkg;
    private String userSource;
    private Integer userNum;
    private Integer totalUserNum;
    private Integer totalInvestedUserNum;
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> usrcUser;

    public DwsDailyPkgUsrcRegister() {
        this.userNum = 0;
        this.totalUserNum = 0;
        this.totalInvestedUserNum = 0;
        this.createTime = new Date();
    }

    public static DwsDailyPkgUsrcRegister of(Long dates, String pkg, String userSource) {
        DwsDailyPkgUsrcRegister dwsDailyPkgUsrcRegister = new DwsDailyPkgUsrcRegister();
        dwsDailyPkgUsrcRegister.setDates(dates);
        dwsDailyPkgUsrcRegister.setPkg(pkg);
        dwsDailyPkgUsrcRegister.setUserSource(userSource);
        return dwsDailyPkgUsrcRegister;
    }

    public void calculate(DwdUserRegister dwdUserRegister) {
        if (CollectionUtils.isEmpty(this.usrcUser)) {
            this.usrcUser = new HashSet<>();
        }
        this.usrcUser.add(dwdUserRegister.getUserId());
        this.userNum = this.usrcUser.size();
    }

    public void setTotal(Integer userNum, Integer investedUserNum) {
        this.totalUserNum = userNum;
        this.totalInvestedUserNum = investedUserNum;
    }
}
