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
@TableName("dws_daily_package_all_register")
public class DwsDailyPackageAllRegister implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
    private String version;
    private Integer userNum;
    private Integer investedUserNum;
    private Integer totalUserNum;
    private Integer totalInvestedUserNum;
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> users;
    @TableField(exist = false)
    private Set<Long> investedUsers;

    public DwsDailyPackageAllRegister() {
        this.userNum = 0;
        this.investedUserNum = 0;
        this.totalUserNum = 0;
        this.totalInvestedUserNum = 0;
        this.createTime = new Date();
    }

    public static DwsDailyPackageAllRegister of(Long dates, String pkg, String version) {
        DwsDailyPackageAllRegister dwsDailyPackageAllRegister = new DwsDailyPackageAllRegister();
        dwsDailyPackageAllRegister.setDates(dates);
        dwsDailyPackageAllRegister.setPkg(pkg);
        dwsDailyPackageAllRegister.setVersion(version);
        return dwsDailyPackageAllRegister;
    }

    public void calculate(DwdUserRegister dwdUserRegister) {
        if (CollectionUtils.isEmpty(this.users)) {
            this.users = new HashSet<>();
        }
        this.users.add(dwdUserRegister.getUserId());
        this.userNum = this.users.size();

        if (dwdUserRegister.isInvestedUser()) {
            if (CollectionUtils.isEmpty(this.investedUsers)) {
                this.investedUsers = new HashSet<>();
            }
            this.investedUsers.add(dwdUserRegister.getUserId());
            this.investedUserNum = this.investedUsers.size();
        }
    }

    public void setTotal(Integer userNum, Integer investedUserNum) {
        this.totalUserNum = userNum;
        this.totalInvestedUserNum = investedUserNum;
    }
}
