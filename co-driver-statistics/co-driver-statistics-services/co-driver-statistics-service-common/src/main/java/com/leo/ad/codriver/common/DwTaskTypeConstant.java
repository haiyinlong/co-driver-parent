package com.leo.ad.codriver.common;

import lombok.Getter;

/**
 * DwTaskTypeConstant
 *
 * @author HaiYinLong
 * @version 2024/11/11 16:48
 **/
@Getter
public enum DwTaskTypeConstant {
    GAME_RECORD_OETA("oetaGameRecord"), USER_LOGIN_OETA("userLoginRecord"), ODS_USER_LOGIN("ods_login_log");

    private final String type;

    DwTaskTypeConstant(String type) {
        this.type = type;
    }
}
