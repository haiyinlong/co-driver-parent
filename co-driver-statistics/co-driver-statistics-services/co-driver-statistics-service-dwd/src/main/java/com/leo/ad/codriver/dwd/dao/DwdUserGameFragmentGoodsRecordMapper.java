package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoodsRecord;

/**
 * @author user
 * @description 针对表【dwd_user_game_fragment_goods_record(用户游戏商品(素材)记录)】的数据库操作Mapper
 * @createDate 2025-05-12 16:42:38
 * @Entity com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoodsRecord
 */
@Mapper
@DS("mysql")
public interface DwdUserGameFragmentGoodsRecordMapper extends BaseMapper<DwdUserGameFragmentGoodsRecord> {

    void deleteByDates(@Param("dates") Integer dates);

    DwCountDTO getOdsStatisticsCount(@Param("dates") Integer dates);

    List<DwdUserGameFragmentGoodsRecord> queryOdsStatisticsInterval(@Param("dates") Integer dates,
        @Param("startId") long startId, @Param("endId") long endId);
}
