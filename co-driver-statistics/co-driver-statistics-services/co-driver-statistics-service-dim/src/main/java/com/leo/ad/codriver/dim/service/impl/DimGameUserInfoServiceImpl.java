package com.leo.ad.codriver.dim.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.dim.dao.DimGameUserInfoMapper;
import com.leo.ad.codriver.dim.entity.DimGameUserInfo;
import com.leo.ad.codriver.dim.service.DimGameUserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DimGameUserInfoServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/27 14:48
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DimGameUserInfoServiceImpl implements DimGameUserInfoService {
    private final DimGameUserInfoMapper dimGameUserInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncGameUser(String odsGameUserId) {
        if (ObjectUtils.isEmpty(odsGameUserId)) {
            return;
        }
        long gameUserId = 0L;

        try {
            gameUserId = new BigDecimal(odsGameUserId).longValue();
            DimGameUserInfo dimGameUserInfo = dimGameUserInfoMapper.getOdsGameUserInfo(gameUserId);
            if (ObjectUtils.isEmpty(dimGameUserInfo)) {
                return;
            }
            if (ObjectUtils.isEmpty(dimGameUserInfo.getId())) {
                dimGameUserInfo.initTime();
                dimGameUserInfoMapper.insert(dimGameUserInfo);
                return;
            }
            dimGameUserInfo.modifyUpdateTime();
            dimGameUserInfoMapper.updateById(dimGameUserInfo);
        } catch (Exception e) {
            log.error("ods_game_user_one_net.id:" + gameUserId + " syncGameUser error", e);
        }
    }

}
