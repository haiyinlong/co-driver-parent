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

@Data
@TableName("dws_daily_pkg_register")
public class DwsDailyPkgRegister implements BaseEntity {

    private Long id;
    private Integer dates;
    private String pkg;
    private Integer userNum;
    private Integer investedUserNum;
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> totalUser;
    @TableField(exist = false)
    private Set<Long> totalInvestedUser;

    public DwsDailyPkgRegister() {
        this.userNum = 0;
        this.investedUserNum = 0;
        this.createTime = new Date();
    }

    public static DwsDailyPkgRegister of(Long dates, String pkg) {
        DwsDailyPkgRegister dwsDailyPkgRegister = new DwsDailyPkgRegister();
        dwsDailyPkgRegister.setDates(dates.intValue());
        dwsDailyPkgRegister.setPkg(pkg);
        return dwsDailyPkgRegister;
    }

    public void calculate(DwdUserRegister dwdUserRegister) {
        if (CollectionUtils.isEmpty(this.totalUser)) {
            this.totalUser = new HashSet<>();
        }
        this.totalUser.add(dwdUserRegister.getUserId());
        this.userNum = this.totalUser.size();

        if (dwdUserRegister.isInvestedUser()) {
            if (CollectionUtils.isEmpty(this.totalInvestedUser)) {
                this.totalInvestedUser = new HashSet<>();
            }
            this.totalInvestedUser.add(dwdUserRegister.getUserId());
            this.investedUserNum = this.totalInvestedUser.size();
        }
    }
}
