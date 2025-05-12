package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoods;

/**
 * @author user
 * @description 针对表【dwd_user_game_goods(dwd用户游戏商品)】的数据库操作Mapper
 * @createDate 2025-05-12 14:07:46
 * @Entity com.leo.ad.codriver.dwd.entity.DwdUserGameGoods
 */
@Mapper
@DS("mysql")
public interface DwdUserGameFragmentGoodsMapper extends BaseMapper<DwdUserGameFragmentGoods> {
    DwCountDTO getOdsStatisticsCount();

    List<DwdUserGameFragmentGoods> queryOdsStatisticsInterval(@Param("dates") int dates, @Param("startId") long startId,
        @Param("endId") long endId);

    DwCountDTO getDwdFragmentStatisticsCount(@Param("dates") int dates);

    List<DwdUserGameFragmentGoods> queryDwdFragmentInterval(@Param("startId") long startId, @Param("endId") long endId);
}
