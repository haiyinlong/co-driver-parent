package com.leo.ad.codriver.dim.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
    private final RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void syncGameUser(String odsGameUserId) {
        if (ObjectUtils.isEmpty(odsGameUserId)) {
            return;
        }
        long gameUserId = 0L;
        String lockKey = getLockKey(odsGameUserId);
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            rLock.lock(10L, TimeUnit.SECONDS);
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
            log.error("userId:" + gameUserId + " syncGameUser error", e);
            throw new RuntimeException(e);
        } finally {
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }

    private String getLockKey(String odsGameUserId) {
        return "dim_game_user_info_" + odsGameUserId;
    }
}
