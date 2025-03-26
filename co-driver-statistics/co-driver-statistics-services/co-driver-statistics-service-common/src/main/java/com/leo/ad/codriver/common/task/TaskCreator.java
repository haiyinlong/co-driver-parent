package com.leo.ad.codriver.common.task;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;

/**
 * 管理器，用于创建任务<br>
 * 根据表名进行获取数据, 并返回一个任务对象
 *
 * @author HaiYinLong
 * @version 2024/11/29 09:47
 **/
public interface TaskCreator {

    /**
     * 创建任务，根据日期的最后任务id值进行创建新任务
     *
     * @param tableName 表名
     * @return
     */
    DwTaskRecord createTask(String tableName);
}
