package com.leo.ad.codriver.dws.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author user
 */
@Data
public class PkgVerAdDTO {
    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private Integer userType;
    private String adFormat;
    private String network;
    private Integer userNum;
    private BigDecimal adIncome;
    private Integer adShowCount;

    public String pkgVersionAdFormat() {
        return pkg + "_" + version + "_" + adFormat;
    }
}
