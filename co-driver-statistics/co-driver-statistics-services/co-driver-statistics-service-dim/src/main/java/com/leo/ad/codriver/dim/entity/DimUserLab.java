
package com.leo.ad.codriver.dim.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dim_user_lab")
public class DimUserLab {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String pkg;
    private String pvc;
    private String svc;
    private Long userId;
    private Long pkgConfigGroupId;
    private String configGroupType;
    private Long configGroupId;
    private String name;
    private Date createTime;
    private Date updateTime;

    public void initDate() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public void updateDate() {
        this.updateTime = new Date();
    }
}
