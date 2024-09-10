package com.leo.ad.codriver.dwd.service;

import com.leo.ad.codriver.dwd.dto.DataChangeDTO;

/**
 * DwdStreamService,每次从mq中获取实时数据进行更新数据到dwd<br>
 *
 * @author HaiYinLong
 * @version 2024/09/10 17:26
 **/
public interface DwdStreamService {

    /**
     * 数据变更数据同步，根据sourceId获取数据，如果不存在就删除数据库中的数据，如果存在就更新到数据库中
     *
     * @param dataChangeDTO
     * @return
     */
    boolean syncChangeData(DataChangeDTO dataChangeDTO);
}
