package com.leo.ad.codriver.dwd.dto;

import lombok.Data;

/**
 * DwdUserEventWithRegisterDateSourceDTO
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:58
 **/
@Data
public class DwdUserEventWithUserSourceDTO extends DwdUserEventWithRegisterDateDTO {
    private String userSource;

    public String getPkgSourceKey() {
        return this.getPkg() + "_" + this.getUserSource();
    }

    public String getPkgVersionSourceKey() {
        return this.getPkg() + "_" + this.getVersion() + "_" + this.getUserSource();
    }
}
