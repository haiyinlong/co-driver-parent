package com.leo.ad.codriver.ads.service;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

/**
 * AdsService, 读取dws层数据进行汇总<br>
 * 通过sql直接join进行汇总统计<br>
 * 每次进行更新不需要删除，再插入
 *
 * @author HaiYinLong
 * @version 2024/04/09 16:22
 **/
public interface AdsService {
    /**
     * 根据日期同步处理数据
     *
     * @param dates 20241010 日期
     */
    void syncData(Integer dates);

    /**
     * 获取需要删除的id集合
     *
     * @param dbList
     * @param values
     * @return
     */
    default List<Long> getNotExistsIds(List<? extends BaseEntity> dbList, List<? extends BaseEntity>... values) {
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
