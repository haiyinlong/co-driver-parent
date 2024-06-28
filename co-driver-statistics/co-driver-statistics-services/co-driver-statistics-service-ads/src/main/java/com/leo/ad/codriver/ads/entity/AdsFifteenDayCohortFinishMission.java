package com.leo.ad.codriver.ads.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("ads_fifteen_day_cohort_finish_mission")
public class AdsFifteenDayCohortFinishMission {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long projectId;
    private Long productId;
    private String productName;
    private Long channelId;
    private String channelName;
    private String pkg;
    private String country;
    private String eventType;
    private String version;
    private Integer userNum;
    private Integer cohort0UserNum;
    private Integer cohort1UserNum;
    private Integer cohort2UserNum;
    private Integer cohort3UserNum;
    private Integer cohort4UserNum;
    private Integer cohort5UserNum;
    private Integer cohort6UserNum;
    private Integer cohort7UserNum;
    private Integer cohort8UserNum;
    private Integer cohort9UserNum;
    private Integer cohort10UserNum;
    private Integer cohort11UserNum;
    private Integer cohort12UserNum;
    private Integer cohort13UserNum;
    private Integer cohort14UserNum;
    private Integer cohortTotalUserNum;
    private BigDecimal cohortTotalRate;
}
