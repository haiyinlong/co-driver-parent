package com.leo.ad.codriver.dim.service;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

/**
 * DimService, 统计维表数据<br>
 *
 * @author HaiYinLong
 * @version 2024/04/18 17:26
 **/
public interface DimService {
    void syncData();

    /**
     * 获取需要删除的id集合
     *
     * @param dbList
     * @param activeList
     * @param newList
     * @return
     */
    default List<Long> getDelIds(List<? extends BaseEntity> dbList, List<? extends BaseEntity> activeList,
        List<? extends BaseEntity> newList) {
        if (CollectionUtils.isEmpty(dbList)) {
            return Collections.emptyList();
        }
        List<Long> dbIds = new java.util.ArrayList<>(dbList.stream().map(BaseEntity::getId).toList());
        if (!CollectionUtils.isEmpty(activeList)) {
            activeList.forEach(item -> dbIds.remove(item.getId()));
        }
        if (!CollectionUtils.isEmpty(newList)) {
            newList.forEach(item -> dbIds.remove(item.getId()));
        }
        return dbIds;
    }
}
