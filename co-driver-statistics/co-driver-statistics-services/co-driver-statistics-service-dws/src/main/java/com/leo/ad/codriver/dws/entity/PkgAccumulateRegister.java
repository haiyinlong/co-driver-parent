package com.leo.ad.codriver.dws.entity;

/**
 * PkgAccumulateRegister
 *
 * @author HaiYinLong
 * @version 2025/03/13 11:49
 **/
public interface PkgAccumulateRegister {
    Integer getRegisterDay();

    default boolean validate180Days() {
        return getRegisterDay() != null && getRegisterDay() >= 0 && getRegisterDay() <= 180;
    }
}
