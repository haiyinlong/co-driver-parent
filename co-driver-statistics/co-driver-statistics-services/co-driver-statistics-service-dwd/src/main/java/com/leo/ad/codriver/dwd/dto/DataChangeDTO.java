package com.leo.ad.codriver.dwd.dto;

import lombok.Data;

/**
 * DataChangeDTO
 *
 * @author HaiYinLong
 * @version 2024/09/10 10:08
 **/
@Data
public class DataChangeDTO {
    private Long sourceId;
    private long timestamp;
    private Integer dates;

    /**
     * 创建一个数据变更对象
     *
     * @param sourceId 源数据id(删除、修改、新增)
     * @return
     */
    public static DataChangeDTO of(Long sourceId) {
        DataChangeDTO dataChangeDTO = new DataChangeDTO();
        dataChangeDTO.setSourceId(sourceId);
        dataChangeDTO.setTimestamp(System.currentTimeMillis());
        return dataChangeDTO;
    }
    public static DataChangeDTO of(Long sourceId, Integer dates) {
        DataChangeDTO dataChangeDTO = new DataChangeDTO();
        dataChangeDTO.setSourceId(sourceId);
        dataChangeDTO.setDates(dates);
        dataChangeDTO.setTimestamp(System.currentTimeMillis());
        return dataChangeDTO;
    }
}
