package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.dwd.entity.DwdUserWithdrawRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_withdraw
 */
@TableName(value = "dws_daily_cohort_pkg_withdraw")
@Data
public class DwsDailyCohortPkgWithdraw implements BaseEntity {
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
     * 提现总数量
     */
    private Long totalRecordNum;

    /**
     * 提现用户数
     */
    private Integer totalUserNum;

    /**
     * 提现中数量
     */
    private Long processRecordNum;

    /**
     * 提现中用户数
     */
    private Integer processUserNum;

    /**
     * 成功提现金额
     */
    private BigDecimal successAmount;

    /**
     * 成功提现手续费
     */
    private BigDecimal successFee;

    /**
     * 成功提现金额含手续费
     */
    private BigDecimal successAmountFee;

    /**
     * 成功提现金额(美元)
     */
    private BigDecimal successChangeAmount;

    /**
     * 成功提现金额含手续费(美元)
     */
    private BigDecimal successChangeAmountFee;

    /**
     * 成功提现手续费(美元)
     */
    private BigDecimal successChangeFee;

    /**
     * 成功提现数量
     */
    private Long successRecordNum;

    /**
     * 成功提现用户数
     */
    private Integer successUserNum;

    /**
     * 提现失败记录数
     */
    private Long failedRecordNum;

    /**
     * 提现失败用户数
     */
    private Integer failedUserNum;

    /**
     * aws提现金额
     */
    private BigDecimal awsAmount;

    /**
     * aws提现金额含手续费
     */
    private BigDecimal awsAmountFee;

    /**
     * aws成功提现手续费
     */
    private BigDecimal awsFee;

    /**
     * aws提现金额(美元)
     */
    private BigDecimal awsChangeAmount;

    /**
     * aws提现金额含手续费(美元)
     */
    private BigDecimal awsChangeAmountFee;

    /**
     * aws成功提现手续费(美元)
     */
    private BigDecimal awsChangeFee;

    /**
     * aws提现数量
     */
    private Long awsRecordNum;

    /**
     * aws提现用户数
     */
    private Integer awsUserNum;

    /**
     * bank提现金额
     */
    private BigDecimal bankAmount;

    /**
     * bank提现金额含手续费
     */
    private BigDecimal bankAmountFee;

    /**
     * bank提现手续费
     */
    private BigDecimal bankFee;

    /**
     * bank提现金额(美元)
     */
    private BigDecimal bankChangeAmount;

    /**
     * bank提现金额含手续费(美元)
     */
    private BigDecimal bankChangeAmountFee;

    /**
     * bank成功提现手续费(美元)
     */
    private BigDecimal bankChangeFee;

    /**
     * bank提现数量
     */
    private Long bankRecordNum;

    /**
     * bank提现用户数
     */
    private Integer bankUserNum;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> totalUser;
    @TableField(exist = false)
    private Set<Long> processUser;
    @TableField(exist = false)
    private Set<Long> successUser;
    @TableField(exist = false)
    private Set<Long> failedUser;
    @TableField(exist = false)
    private Set<Long> awsUser;
    @TableField(exist = false)
    private Set<Long> bankUser;

    public DwsDailyCohortPkgWithdraw(Integer dates, Integer registerDates, Integer cohortDay, String pkg) {
        this.dates = dates;
        this.registerDates = registerDates;
        this.cohortDay = cohortDay;
        this.pkg = pkg;
        this.createTime = new Date();
        this.totalRecordNum = 0L;
        this.totalUserNum = 0;
        this.processRecordNum = 0L;
        this.processUserNum = 0;
        this.successAmount = BigDecimal.ZERO;
        this.successFee = BigDecimal.ZERO;
        this.successAmountFee = BigDecimal.ZERO;
        this.successChangeAmount = BigDecimal.ZERO;
        this.successChangeAmountFee = BigDecimal.ZERO;
        this.successChangeFee = BigDecimal.ZERO;
        this.successRecordNum = 0L;
        this.successUserNum = 0;
        this.failedRecordNum = 0L;
        this.failedUserNum = 0;
        this.awsAmount = BigDecimal.ZERO;
        this.awsAmountFee = BigDecimal.ZERO;
        this.awsFee = BigDecimal.ZERO;
        this.awsChangeAmount = BigDecimal.ZERO;
        this.awsChangeAmountFee = BigDecimal.ZERO;
        this.awsRecordNum = 0L;
        this.awsUserNum = 0;
        this.bankAmount = BigDecimal.ZERO;
        this.bankAmountFee = BigDecimal.ZERO;
        this.bankFee = BigDecimal.ZERO;
        this.bankChangeAmount = BigDecimal.ZERO;
        this.bankChangeAmountFee = BigDecimal.ZERO;
        this.bankRecordNum = 0L;
        this.bankUserNum = 0;
        this.totalUser = new HashSet<>();
        this.processUser = new HashSet<>();
        this.failedUser = new HashSet<>();
        this.successUser = new HashSet<>();
        this.awsUser = new HashSet<>();
        this.bankUser = new HashSet<>();
    }

    public static DwsDailyCohortPkgWithdraw of(Integer dates, Integer registerDates, Integer registerDay, String pkg) {
        return new DwsDailyCohortPkgWithdraw(dates, registerDates, registerDay, pkg);
    }

    public void calculate(DwdUserWithdrawRecord dwdUserWithdrawRecord) {
        if (null == dwdUserWithdrawRecord) {
            return;
        }
        this.totalUser.add(dwdUserWithdrawRecord.getUserId());
        this.totalUserNum = this.totalUser.size();
        this.totalRecordNum += 1;
        if (0 == dwdUserWithdrawRecord.getStatus()) {
            this.processUser.add(dwdUserWithdrawRecord.getUserId());
            this.processUserNum = this.processUser.size();
            this.processRecordNum += 1;
        } else if (1 == dwdUserWithdrawRecord.getStatus()) {
            this.successUser.add(dwdUserWithdrawRecord.getUserId());
            this.successUserNum = this.successUser.size();
            this.successRecordNum += 1;
            this.successAmount = BigDecimalUtils.add(this.successAmount, dwdUserWithdrawRecord.getAmount());
            this.successFee = BigDecimalUtils.add(this.successFee, dwdUserWithdrawRecord.getFee());
            this.successAmountFee = BigDecimalUtils.add(this.successAmount, this.successFee);
            this.successChangeAmount =
                BigDecimalUtils.add(this.successChangeAmount, dwdUserWithdrawRecord.getChangeAmount());
            this.successChangeAmountFee =
                BigDecimalUtils.add(this.successChangeAmountFee, dwdUserWithdrawRecord.getChangeTotalAmount());
            this.successChangeFee = BigDecimalUtils.add(this.successChangeFee, dwdUserWithdrawRecord.getChangeFee());

            if ("AWS".equalsIgnoreCase(dwdUserWithdrawRecord.getWithdrawType())) {
                this.awsUser.add(dwdUserWithdrawRecord.getUserId());
                this.awsUserNum = this.awsUser.size();
                this.awsAmount = BigDecimalUtils.add(this.awsAmount, dwdUserWithdrawRecord.getAmount());
                this.awsFee = BigDecimalUtils.add(this.awsFee, dwdUserWithdrawRecord.getFee());
                this.awsAmountFee = BigDecimalUtils.add(this.awsAmount, this.awsFee);
                this.awsChangeAmount =
                    BigDecimalUtils.add(this.awsChangeAmount, dwdUserWithdrawRecord.getChangeAmount());
                this.awsChangeAmountFee =
                    BigDecimalUtils.add(this.awsChangeAmountFee, dwdUserWithdrawRecord.getChangeTotalAmount());
                this.awsChangeFee = BigDecimalUtils.add(this.awsChangeFee, dwdUserWithdrawRecord.getChangeFee());
            } else if ("BANK".equalsIgnoreCase(dwdUserWithdrawRecord.getWithdrawType())) {
                this.bankUser.add(dwdUserWithdrawRecord.getUserId());
                this.bankUserNum = this.bankUser.size();
                this.bankAmount = BigDecimalUtils.add(this.bankAmount, dwdUserWithdrawRecord.getAmount());
                this.bankFee = BigDecimalUtils.add(this.bankFee, dwdUserWithdrawRecord.getFee());
                this.bankAmountFee = BigDecimalUtils.add(this.bankAmount, this.bankFee);
                this.bankChangeAmount =
                    BigDecimalUtils.add(this.bankChangeAmount, dwdUserWithdrawRecord.getChangeAmount());
                this.bankChangeAmountFee =
                    BigDecimalUtils.add(this.bankChangeAmountFee, dwdUserWithdrawRecord.getChangeTotalAmount());
                this.bankChangeFee = BigDecimalUtils.add(this.bankChangeFee, dwdUserWithdrawRecord.getChangeFee());
            }

        } else if (2 == dwdUserWithdrawRecord.getStatus()) {
            this.failedUser.add(dwdUserWithdrawRecord.getUserId());
            this.failedUserNum = this.failedUser.size();
            this.failedRecordNum += 1;
        }

    }
}
