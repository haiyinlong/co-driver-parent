package com.leo.ad.codriver.dwd.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户游戏商品(素材)记录
 *
 * @TableName dwd_user_game_fragment_goods_record
 */
@TableName(value = "dwd_user_game_fragment_goods_record")
@Data
public class DwdUserGameFragmentGoodsRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -2811147547047918032L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期:0时区
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品
     */
    private Integer goods;

    /**
     * 记录类型 1：新增；0：扣减；
     */
    private Integer recordType;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 用户版本
     */
    private String pvn;

    /**
     * 来源类型
     */
    private Integer sourceType;

    /**
     * 来源id
     */
    private Long sourceId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
