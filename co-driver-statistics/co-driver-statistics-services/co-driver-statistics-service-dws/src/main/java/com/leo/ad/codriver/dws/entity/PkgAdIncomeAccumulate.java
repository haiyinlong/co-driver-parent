package com.leo.ad.codriver.dws.entity;

/**
 * PkgAdIncomeAccumulate
 *
 * @author HaiYinLong
 * @version 2025/03/13 11:49
 **/
public abstract class PkgAdIncomeAccumulate extends PkgAdIncome {
    abstract Integer getRegisterDay();

    public boolean validate180Days() {
        return getRegisterDay() != null && getRegisterDay() > 0 && getRegisterDay() <= 180;
    }
}
