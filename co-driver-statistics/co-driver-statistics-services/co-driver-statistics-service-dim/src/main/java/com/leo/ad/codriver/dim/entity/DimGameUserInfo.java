package com.leo.ad.codriver.dim.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dim_game_user_info")
public class DimGameUserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private Long gameId;
    private String name;
    private String type;
    private Long level;
    private Long playedNum;
    private Date createTime;
    private Date updateTime;

    public void initTime() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public void modifyUpdateTime() {
        this.updateTime = new Date();
    }
}
