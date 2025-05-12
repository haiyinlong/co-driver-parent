package com.leo.ad.codriver.dwd.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dwd用户游戏商品
 *
 * @TableName dwd_user_game_goods
 */
@TableName(value = "dwd_user_game_goods")
@Data
public class DwdUserGameGoods implements BaseEntity {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 商品
     */
    private Integer goods;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总数量
     */
    private Integer totalNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public void updateDates(Integer dates) {
        this.dates = dates;
        this.createTime = LocalDateTime.now();
    }
}
