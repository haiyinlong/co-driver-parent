package com.leo.ad.codriver.common.dao.enetity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.DwTaskTypeConstant;

import lombok.Data;

/**
 * 数据同步记录,针对大的执行记录
 *
 * @author haiYinLong
 * @TableName dw_task_record
 */
@TableName(value = "dw_task_record")
@Data
public class DwTaskRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -3652609887707523000L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 0时区日期
     */
    private Integer dates;

    /**
     * 任务type,各个任务的type
     */
    private String type;

    /**
     * 状态:1进行中；2执行完成;3:异常终止
     */
    private Integer status;

    /**
     * 开始id
     */
    private Long startId;

    /**
     * 结束id
     */
    private Long endId;

    /**
     * 执行中的id
     */
    private Long processId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public DwTaskRecord() {
        this.processId = 0L;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.status = 1;
    }

    public static DwTaskRecord ofOetaGameRecord(Integer dates, Long startId, Long endId) {
        DwTaskRecord dwTaskRecord = new DwTaskRecord();
        dwTaskRecord.setDates(dates);
        dwTaskRecord.setType(DwTaskTypeConstant.GAME_RECORD_OETA);
        dwTaskRecord.setStartId(startId);
        dwTaskRecord.setEndId(endId);
        dwTaskRecord.setStatus(1);
        return dwTaskRecord;
    }

    public void process(long processId) {
        if (processId >= this.endId) {
            this.setProcessId(this.endId);
            this.status = 2;
        } else {
            this.setProcessId(processId);
            this.status = 1;
        }
        this.updateTime = LocalDateTime.now();
    }

}
