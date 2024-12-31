package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_hm_game_record")
public class DwdHmGameRecord {
    @TableId(type = IdType.AUTO)
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
