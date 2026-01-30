package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dwd_user_ad_record")
public class DwdUserAdRecord implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long sourceId;
    private Integer dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private String adFormat;
    private String network;
    private Long adExhibit;
    private BigDecimal revenue;
    private Date sourceCreateTime;
    private Date createTime;
    private Integer registerDates;
    private Integer registerDay;
    private Integer registerCohortDay;

    @TableField(exist = false)
    private String userSource;

    public void init() {
        this.createTime = new Date();
    }

    public String getVersion() {
        if (ObjectUtils.isEmpty(this.version)) {
            return "";
        }
        return version;
    }

    public boolean isNetworkApplovinDirectsold() {
        return "APPLOVIN_DIRECTSOLD".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isNetworkCustomNetworkSdk() {
        return "CUSTOM_NETWORK_SDK".equalsIgnoreCase(this.getNetwork()) || "OTER_NET".equalsIgnoreCase(
            this.getNetwork());
    }

    public boolean isApplovinExchange() {
        return "APPLOVIN_EXCHANGE".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isApplovinNetwork() {
        return "APPLOVIN_NETWORK".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isMintegralBidding() {
        return "MINTEGRAL_BIDDING".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isVungleBidding() {
        return "VUNGLE_BIDDING".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isFacebookNetwork() {
        return "FACEBOOK_NETWORK".equalsIgnoreCase(this.getNetwork());
    }

    public boolean isAdFormatReward() {
        return "reward".equalsIgnoreCase(this.getAdFormat());
    }

    public boolean isMrec() {
        return "mrec".equalsIgnoreCase(this.getAdFormat());
    }

    public boolean isInter() {
        return "inter".equalsIgnoreCase(this.getAdFormat());
    }

    public boolean isBanner() {
        return "banner".equalsIgnoreCase(this.getAdFormat());
    }
}
