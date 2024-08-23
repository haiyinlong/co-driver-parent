package com.leo.ad.codriver.dim.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.dim.dao.DimUserInfoMapper;
import com.leo.ad.codriver.dim.entity.DimUserInfo;
import com.leo.ad.codriver.dim.entity.RealTimeUserInfoDTO;
import com.leo.ad.codriver.dim.service.DimUserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DimUserInfoServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/06/24 16:51
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DimUserInfoServiceImpl implements DimUserInfoService {
    private final DimUserInfoMapper dimUserInfoMapper;

    /**
     * 监听ods_user数据变更，更新就同步修改dim_user_info
     *
     * @param odsUserChangeId 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncUserInfo(String odsUserChangeId) {
        if (ObjectUtils.isEmpty(odsUserChangeId)) {
            return;
        }
        long userId = Long.parseLong(odsUserChangeId);
        // 根据id查询当前最新数据更新用户信息表
        RealTimeUserInfoDTO realTimeUserInfo = dimUserInfoMapper.queryOdsRealTimeUserInfo(userId);
        DimUserInfo userInfo = dimUserInfoMapper.getUserInfoByUserId(userId);
        if (ObjectUtils.isEmpty(realTimeUserInfo)) {
            return;
        }
        if (ObjectUtils.isEmpty(userInfo)) {
            userInfo = DimUserInfo.createUserInfo(realTimeUserInfo);
            dimUserInfoMapper.insert(userInfo);
            log.info("{}用户新增", userInfo);
        } else {
            userInfo.updateUserInfo(realTimeUserInfo);
            dimUserInfoMapper.updateById(userInfo);
            log.info("{}用户更新", userInfo);
        }
    }
}
