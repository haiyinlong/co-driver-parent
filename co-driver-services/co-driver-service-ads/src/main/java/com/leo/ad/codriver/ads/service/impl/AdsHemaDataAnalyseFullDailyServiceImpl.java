package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsHemaDataAnalyseFullDailyMapper;
import com.leo.ad.codriver.ads.entity.AdsHemaDataAnalyseFullDaily;
import com.leo.ad.codriver.ads.entity.AdsHemaDataAnalyseFullDailyDTO;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 河马大盘数据统计报表,区分活跃和新增维度
 *
 * @author HaiYinLong
 * @version 2024/04/09 16:23
 **/
@Service
@AllArgsConstructor
public class AdsHemaDataAnalyseFullDailyServiceImpl
        implements AdsService {

    private final AdsHemaDataAnalyseFullDailyMapper adsHemaDataAnalyseFullDailyMapper;
    private final DwBatchMapper<AdsHemaDataAnalyseFullDaily, AdsHemaDataAnalyseFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsHemaDataAnalyseFullDaily")
    @Transactional(rollbackFor = Exception.class)
    public void syncData(Integer dates) {
        adsHemaDataAnalyseFullDailyMapper.delete(dates);
        // 统计活跃维度
        List<AdsHemaDataAnalyseFullDailyDTO> dataAnalyseFullDailyDTOS =
                adsHemaDataAnalyseFullDailyMapper.queryStatisticsActiveList(dates);
        List<AdsHemaDataAnalyseFullDaily> dataAnalyseFullDailyList;
        if (!CollectionUtils.isEmpty(dataAnalyseFullDailyDTOS)) {
            // 业务处理
            dataAnalyseFullDailyList = calculateHemaDataAnalyse(dataAnalyseFullDailyDTOS);
            batchMapper.batchInsert(dataAnalyseFullDailyList, AdsHemaDataAnalyseFullDailyMapper.class);
        }
        // 统计新增维度
        dataAnalyseFullDailyDTOS = adsHemaDataAnalyseFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(dataAnalyseFullDailyDTOS)) {
            // 业务处理
            dataAnalyseFullDailyList = calculateHemaDataAnalyse(dataAnalyseFullDailyDTOS);
            batchMapper.batchInsert(dataAnalyseFullDailyList, AdsHemaDataAnalyseFullDailyMapper.class);
        }
    }

    private List<AdsHemaDataAnalyseFullDaily>
    calculateHemaDataAnalyse(List<AdsHemaDataAnalyseFullDailyDTO> dataAnalyseFullDailyDTOS) {
        if (CollectionUtils.isEmpty(dataAnalyseFullDailyDTOS)) {
            return new ArrayList<>();
        }
        // 转化对象和计算
        return dataAnalyseFullDailyDTOS.stream().map(dataAnalyseFullDailyDTO -> {
            // 初始化大盘数据
            AdsHemaDataAnalyseFullDaily adsHemaDataAnalyseFullDaily =
                    AdsHemaDataAnalyseFullDaily.of(dataAnalyseFullDailyDTO.getDates(),
                            dataAnalyseFullDailyDTO.getChannelId(), dataAnalyseFullDailyDTO.getChannelName(),
                            dataAnalyseFullDailyDTO.getPkg(), dataAnalyseFullDailyDTO.getCountry(),
                            dataAnalyseFullDailyDTO.getVersion(), dataAnalyseFullDailyDTO.getUserType(),
                            dataAnalyseFullDailyDTO.getLoginUserNum(), dataAnalyseFullDailyDTO.getRegisterUserNum());

            // 计算ludo
            adsHemaDataAnalyseFullDaily.calculateLudo(dataAnalyseFullDailyDTO.getMatchPageShowUserNum(),
                    dataAnalyseFullDailyDTO.getGameStartNum(), dataAnalyseFullDailyDTO.getGameFinishTotalTime(),
                    dataAnalyseFullDailyDTO.getPromotionTotalCoin(), dataAnalyseFullDailyDTO.getPromotionTotalLifeGain(),
                    dataAnalyseFullDailyDTO.getLudoTotalAmount());
            // 计算slot
            adsHemaDataAnalyseFullDaily.calculateSlot(dataAnalyseFullDailyDTO.getGameEnterOnlyUserNum(),
                    dataAnalyseFullDailyDTO.getGameSlotsBetUserNum(), dataAnalyseFullDailyDTO.getGameSlotsBetNum(),
                    dataAnalyseFullDailyDTO.getGameSlotsTotalBet(), dataAnalyseFullDailyDTO.getGoldGameEnterOnlyUserNum(),
                    dataAnalyseFullDailyDTO.getGoldGameSlotsSpinTotalBet(),
                    dataAnalyseFullDailyDTO.getGoldGameSlotsSpinNum(),
                    dataAnalyseFullDailyDTO.getSlotDeductionTotalAmount(),
                    dataAnalyseFullDailyDTO.getSlotAdditionTotalAmount());
            // 计算积分墙
            adsHemaDataAnalyseFullDaily.calculateScore(dataAnalyseFullDailyDTO.getShowOfferPageUserNum(),
                    dataAnalyseFullDailyDTO.getClickTaskUserNum());
            // 计算落地页
            adsHemaDataAnalyseFullDaily.calculateDownloading(dataAnalyseFullDailyDTO.getWebLoadingUserNum(),
                    dataAnalyseFullDailyDTO.getDownloadPagShowUserNum(),
                    dataAnalyseFullDailyDTO.getDownloadPagClickUserNum());
            // 计算提现页
            adsHemaDataAnalyseFullDaily.calculateWithdrawPage(dataAnalyseFullDailyDTO.getShowWithdrawPageUserNum());
            // 计算提现消耗
            adsHemaDataAnalyseFullDaily.calculateWithdrawAmount(dataAnalyseFullDailyDTO.getWithdrawTotalAmount());
            // 计算投放花费: 根据人员数据进行平分
            adsHemaDataAnalyseFullDaily.calculatePromotionCost(dataAnalyseFullDailyDTO.getPkgPromotionTotalCost(),
                    dataAnalyseFullDailyDTO.getRegisterPkgUserNum());
            // 计算导流转化 依赖投放花费 和 提现消耗
            adsHemaDataAnalyseFullDaily.calculateConversion(dataAnalyseFullDailyDTO.getConversionUserNum());
            // 计算导流付费数据 依赖投放花费 和 提现消耗 导流Revenue(d0)
            adsHemaDataAnalyseFullDaily.calculateConversionPayment(dataAnalyseFullDailyDTO.getPaymentAmount(),
                    dataAnalyseFullDailyDTO.getPaymentUserNum());
            // 设置留存数据
            adsHemaDataAnalyseFullDaily.calculateRetention(dataAnalyseFullDailyDTO.getRetention1(),
                    dataAnalyseFullDailyDTO.getRetention7());
            return adsHemaDataAnalyseFullDaily;
        }).collect(Collectors.toList());
    }

}
