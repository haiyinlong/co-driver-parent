package com.leo.ad.codriver.dws.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserShareRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_share
 */
@TableName(value = "dws_daily_cohort_pkg_share")
@Data
public class DwsDailyCohortPkgShare implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 同期群天数:d0,d1,d2
     */
    private Integer cohortDay;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 归因用户数量(自动邀请归因)
     */
    private Integer invitationNum;

    /**
     * 填写编码用户数量
     */
    private Integer fillCodeNum;

    /**
     * 总用户数量
     */
    private Integer totalUserNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private Set<Long> totalUser;
    @TableField(exist = false)
    private Set<Long> invitationUser;
    @TableField(exist = false)
    private Set<Long> fillCodeUser;

    public DwsDailyCohortPkgShare(Integer dates, Integer registerDates, Integer cohortDay, String pkg) {
        this.dates = dates;
        this.registerDates = registerDates;
        this.cohortDay = cohortDay;
        this.pkg = pkg;
        this.createTime = LocalDateTime.now();
        this.totalUserNum = 0;
        this.invitationNum = 0;
        this.fillCodeNum = 0;
        this.totalUser = new HashSet<>();
        this.invitationUser = new HashSet<>();
        this.fillCodeUser = new HashSet<>();
    }

    public static DwsDailyCohortPkgShare of(Integer dates, Integer registerDates, Integer cohortDay, String pkg) {
        return new DwsDailyCohortPkgShare(dates, registerDates, cohortDay, pkg);
    }

    public void calculate(DwdUserShareRecord dwdUserShareRecord) {
        if (null == dwdUserShareRecord) {
            return;
        }
        this.totalUser.add(dwdUserShareRecord.getUserId());
        this.totalUserNum = this.totalUser.size();
        if (1 == dwdUserShareRecord.getSource()) {
            this.invitationUser.add(dwdUserShareRecord.getUserId());
            this.invitationNum = this.invitationUser.size();
        } else if (2 == dwdUserShareRecord.getSource()) {
            this.fillCodeUser.add(dwdUserShareRecord.getUserId());
            this.fillCodeNum = this.fillCodeUser.size();
        }
    }
}
