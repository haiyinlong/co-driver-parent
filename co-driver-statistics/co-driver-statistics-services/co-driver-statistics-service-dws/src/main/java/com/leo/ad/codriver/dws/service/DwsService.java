package com.leo.ad.codriver.dws.service;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

/**
 * DwsService, 读取dwd层数据进行汇总写入到dws层<br>
 * <b>改为</b>： 循环获取数据明细；进行汇总处理；对比已有数据进行新增、修改、删除操作<br>
 * 减少数据库压力
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
public interface DwsService {
    /**
     * 根据日期同步处理数据<br/>
     * 删除历史数据进行重算，保证数据正确性
     *
     * @param dates 20241010 日期
     */
    void syncData(Integer dates);

    default List<Long> getDelIds(List<? extends BaseEntity> dbList, List<? extends BaseEntity>... values) {
        if (CollectionUtils.isEmpty(dbList)) {
            return Collections.emptyList();
        }
        List<Long> dbIds = new java.util.ArrayList<>(dbList.stream().map(BaseEntity::getId).toList());
        for (List<? extends BaseEntity> value : values) {
            if (!CollectionUtils.isEmpty(value)) {
                value.forEach(item -> dbIds.remove(item.getId()));
            }
        }
        return dbIds;
    }

}
