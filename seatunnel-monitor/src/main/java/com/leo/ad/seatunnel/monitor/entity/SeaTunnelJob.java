package com.leo.ad.seatunnel.monitor.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.seatunnel.monitor.util.CommandUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@TableName("seatunnel_job")
public class SeaTunnelJob {

    private final static String EXECUTE_COMMAND =
        "nohup bin/seatunnel.sh -m cluster --config config/%s  -r %s > logs/%s 2>&1 &";

    @TableId(type = IdType.AUTO)
    private Long id;
    private String jobId;
    private String jobName;
    private String description;
    private String fileName;
    private String content;
    private Long enabledFlag;
    private Long deletedFlag;
    private Date createTime;
    private Date updateTime;

    public void start(String seaTunnelHome) {
        // 组装执行命令
        String executeCommand = getExecuteCommand();
        // 执行命令
        String commandResponse = CommandUtils.executeCommand(seaTunnelHome, executeCommand);
        log.info("{} 执行命令：{}  返回结果:[{}]", this.jobId, executeCommand, commandResponse);
        this.updateTime = new Date();
    }

    private String getExecuteCommand() {
        String logFileName = getLogFileName();
        return String.format(EXECUTE_COMMAND, this.fileName, this.jobId, logFileName);
    }

    private String getLogFileName() {
        return this.fileName.substring(0, this.fileName.lastIndexOf(".")).concat(".log");
    }
}
