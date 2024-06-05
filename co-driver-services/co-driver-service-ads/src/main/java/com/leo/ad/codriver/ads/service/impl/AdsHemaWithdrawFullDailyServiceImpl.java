package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsHemaWithdrawFullDailyMapper;
import com.leo.ad.codriver.ads.entity.AdsHemaWithdrawFullDaily;
import com.leo.ad.codriver.ads.entity.AdsHemaWithdrawFullDailyDTO;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
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
public class AdsHemaWithdrawFullDailyServiceImpl
        implements AdsService {

    private final AdsHemaWithdrawFullDailyMapper adsHemaWithdrawFullDailyMapper;
    private final DwBatchMapper<AdsHemaWithdrawFullDaily, AdsHemaWithdrawFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsHemaWithdrawFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        adsHemaWithdrawFullDailyMapper.delete(dates);
        // 统计活跃维度
        List<AdsHemaWithdrawFullDailyDTO> withdrawFullDailyDTOList =
                adsHemaWithdrawFullDailyMapper.queryStatisticsActiveList(dates);
        List<AdsHemaWithdrawFullDaily> hemaWithdrawFullDailies;
        if (!CollectionUtils.isEmpty(withdrawFullDailyDTOList)) {
            // 业务处理
            hemaWithdrawFullDailies = calculateHemaDataAnalyse(withdrawFullDailyDTOList);
            batchMapper.batchInsert(hemaWithdrawFullDailies, AdsHemaWithdrawFullDailyMapper.class);
        }
        // 统计新增维度
        withdrawFullDailyDTOList = adsHemaWithdrawFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailyDTOList)) {
            // 业务处理
            hemaWithdrawFullDailies = calculateHemaDataAnalyse(withdrawFullDailyDTOList);
            batchMapper.batchInsert(hemaWithdrawFullDailies, AdsHemaWithdrawFullDailyMapper.class);
        }
    }

    private List<AdsHemaWithdrawFullDaily>
    calculateHemaDataAnalyse(List<AdsHemaWithdrawFullDailyDTO> withdrawFullDailyDTOList) {
        if (CollectionUtils.isEmpty(withdrawFullDailyDTOList)) {
            return new ArrayList<>();
        }
        // 转化对象和计算
        return withdrawFullDailyDTOList.stream().map(dataAnalyseFullDailyDTO -> {
            // 初始化提现数据
            AdsHemaWithdrawFullDaily adsHemaWithdrawFullDaily =
                    AdsHemaWithdrawFullDaily.of(dataAnalyseFullDailyDTO.getDates(), dataAnalyseFullDailyDTO.getChannelId(),
                            dataAnalyseFullDailyDTO.getChannelName(), dataAnalyseFullDailyDTO.getPkg(),
                            dataAnalyseFullDailyDTO.getCountry(), dataAnalyseFullDailyDTO.getVersion(),
                            dataAnalyseFullDailyDTO.getUserType(), dataAnalyseFullDailyDTO.getLoginUserNum(),
                            dataAnalyseFullDailyDTO.getRegisterUserNum());

            // 计算提现页渗透率
            adsHemaWithdrawFullDaily.calculateWithdrawPage(dataAnalyseFullDailyDTO.getShowWithdrawPageUserNum());
            // 计算提现统计
            adsHemaWithdrawFullDaily.calculateWithdrawAmount(dataAnalyseFullDailyDTO.getWithdrawUserNum(),
                    dataAnalyseFullDailyDTO.getWithdrawSuccessUserNum(), dataAnalyseFullDailyDTO.getWithdrawBankAmountFee(),
                    dataAnalyseFullDailyDTO.getWithdrawAwsAmountFee());
            // 计算发放现金
            adsHemaWithdrawFullDaily.calculateSendAmount(dataAnalyseFullDailyDTO.getLudoTotalAmount(),
                    dataAnalyseFullDailyDTO.getLudoUserNum(), dataAnalyseFullDailyDTO.getSlotAdditionTotalAmount(),
                    dataAnalyseFullDailyDTO.getSlotAdditionUserNum(), dataAnalyseFullDailyDTO.getSlotDeductionTotalAmount(),
                    dataAnalyseFullDailyDTO.getSlotDeductionUserNum(),
                    dataAnalyseFullDailyDTO.getNewUserRechargeGiftTotalAmount(),
                    dataAnalyseFullDailyDTO.getPiggyBankBreakTotalAmount(),
                    dataAnalyseFullDailyDTO.getPiggyBankBreakUserNum(),
                    dataAnalyseFullDailyDTO.getDailyCheckinTotalAmount(), dataAnalyseFullDailyDTO.getDailyCheckinUserNum(),
                    dataAnalyseFullDailyDTO.getSlotRankingTotalAmount(), dataAnalyseFullDailyDTO.getSlotRankingUserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRechargeFirstTotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRechargeFirstUserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge200TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge200UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge300TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge300UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge500TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge500UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge1000TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge1000UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge2000TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge2000UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge3000TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge3000UserNum(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge5000TotalAmount(),
                    dataAnalyseFullDailyDTO.getOfferTaskRecharge5000UserNum(),
                    dataAnalyseFullDailyDTO.getSuperDiceRollTotalAmount(),
                    dataAnalyseFullDailyDTO.getSuperDiceRollUserNum(),
                    dataAnalyseFullDailyDTO.getLuckyScratchCardTotalAmount(),
                    dataAnalyseFullDailyDTO.getLuckyScratchCardUserNum());
            // 计算幸运转盘发放现金和人均
            adsHemaWithdrawFullDaily.calculateSpinReward(dataAnalyseFullDailyDTO.getSpinReward1000TotalAmount(),
                    dataAnalyseFullDailyDTO.getSpinReward1000UserNum());
            // 计算幸运宝箱发放现金和人均
            adsHemaWithdrawFullDaily.calculateSpinChest(dataAnalyseFullDailyDTO.getSpin5ChestTotalAmount(),
                    dataAnalyseFullDailyDTO.getSpin5ChestUserNum(), dataAnalyseFullDailyDTO.getSpin10ChestTotalAmount(),
                    dataAnalyseFullDailyDTO.getSpin10ChestUserNum(), dataAnalyseFullDailyDTO.getSpin15ChestTotalAmount(),
                    dataAnalyseFullDailyDTO.getSpin15ChestUserNum(), dataAnalyseFullDailyDTO.getSpin20ChestTotalAmount(),
                    dataAnalyseFullDailyDTO.getSpin20ChestUserNum());

            // 计算当日在线用户的余额
            adsHemaWithdrawFullDaily.calculateBalance(dataAnalyseFullDailyDTO.getBalance0To1000UserNum(),
                    dataAnalyseFullDailyDTO.getBalance1000To3000UserNum(),
                    dataAnalyseFullDailyDTO.getBalance3000To5000UserNum(),
                    dataAnalyseFullDailyDTO.getBalanceGreaterThan5000UserNum());
            return adsHemaWithdrawFullDaily;
        }).collect(Collectors.toList());
    }

}
