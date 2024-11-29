package com.leo.ad.codriver.common.service;

import java.util.List;

import com.leo.ad.codriver.common.dao.entity.DwSync;

/**
 * DwSyncService
 *
 * @author HaiYinLong
 * @version 2024/11/29 14:37
 **/
public interface DwSyncService {
    List<DwSync> queryList();

    DwSync getSyncById(Long id);
}
