package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;

import com.leo.ad.codriver.common.util.BigDecimalUtils;

import lombok.Data;

/**
 * PkgAccumulateWithdraw
 *
 * @author HaiYinLong
 * @version 2025/03/13 12:21
 **/
@Data
public class PkgAccumulateWithdraw {

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
     * 卢比转美元汇率
     */
    private BigDecimal exchangeRate;

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
     * 成功提现金额
     */
    private BigDecimal accumulateSuccessAmount;

    /**
     * 成功提现手续费
     */
    private BigDecimal accumulateSuccessFee;

    /**
     * 成功提现金额含手续费
     */
    private BigDecimal accumulateSuccessAmountFee;

    /**
     * 成功提现金额(美元)
     */
    private BigDecimal accumulateSuccessChangeAmount;

    /**
     * 成功提现金额含手续费(美元)
     */
    private BigDecimal accumulateSuccessChangeAmountFee;

    /**
     * 成功提现手续费(美元)
     */
    private BigDecimal accumulateSuccessChangeFee;

    protected void todayInit() {
        this.successAmount = BigDecimal.ZERO;
        this.successFee = BigDecimal.ZERO;
        this.successAmountFee = BigDecimal.ZERO;
        this.exchangeRate = BigDecimal.ZERO;
        this.successChangeAmount = BigDecimal.ZERO;
        this.successChangeAmountFee = BigDecimal.ZERO;
        this.successChangeFee = BigDecimal.ZERO;
    }

    protected void accumulate(PkgAccumulateWithdraw todayWithdraw) {
        this.accumulateSuccessAmount =
            BigDecimalUtils.add(this.accumulateSuccessAmount, todayWithdraw.getSuccessAmount());
        this.accumulateSuccessFee = BigDecimalUtils.add(this.accumulateSuccessFee, todayWithdraw.getSuccessFee());
        this.accumulateSuccessAmountFee =
            BigDecimalUtils.add(this.accumulateSuccessAmountFee, todayWithdraw.getSuccessAmountFee());
        this.accumulateSuccessChangeAmount =
            BigDecimalUtils.add(this.accumulateSuccessChangeAmount, todayWithdraw.getSuccessChangeAmount());
        this.accumulateSuccessChangeAmountFee =
            BigDecimalUtils.add(this.accumulateSuccessChangeAmountFee, todayWithdraw.getSuccessChangeAmountFee());
        this.accumulateSuccessChangeFee =
            BigDecimalUtils.add(this.accumulateSuccessChangeFee, todayWithdraw.getSuccessChangeFee());
        this.exchangeRate = todayWithdraw.getExchangeRate();
        this.successChangeAmount = todayWithdraw.getSuccessChangeAmount();
        this.successChangeAmountFee = todayWithdraw.getSuccessChangeAmountFee();
        this.successChangeFee = todayWithdraw.getSuccessChangeFee();
        this.successAmount = todayWithdraw.getSuccessAmount();
        this.successFee = todayWithdraw.getSuccessFee();
        this.successAmountFee = todayWithdraw.getSuccessAmountFee();
    }

    protected void accumulate() {
        this.accumulateSuccessAmount = BigDecimalUtils.add(this.accumulateSuccessAmount, this.successAmount);
        this.accumulateSuccessFee = BigDecimalUtils.add(this.accumulateSuccessFee, this.successFee);
        this.accumulateSuccessAmountFee = BigDecimalUtils.add(this.accumulateSuccessAmountFee, this.successAmountFee);
        this.accumulateSuccessChangeAmount =
            BigDecimalUtils.add(this.accumulateSuccessChangeAmount, this.successChangeAmount);
        this.accumulateSuccessChangeAmountFee =
            BigDecimalUtils.add(this.accumulateSuccessChangeAmountFee, this.successChangeAmountFee);
        this.accumulateSuccessChangeFee = BigDecimalUtils.add(this.accumulateSuccessChangeFee, this.successChangeFee);
    }
}
