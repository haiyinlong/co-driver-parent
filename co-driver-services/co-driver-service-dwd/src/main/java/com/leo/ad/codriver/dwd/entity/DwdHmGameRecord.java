package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dwd_hm_game_record")
public class DwdHmGameRecord {

    private Long id;
    private Long sourceId;
    private Long userId;
    private Integer dates;
    private String version;
    private String eventId;
    private String eventMsg;
    private BigDecimal value;
    private Date createTime;

}
