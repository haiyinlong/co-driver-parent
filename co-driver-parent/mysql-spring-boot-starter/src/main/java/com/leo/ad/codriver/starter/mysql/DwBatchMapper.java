package com.leo.ad.codriver.starter.mysql;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwBatchMapper
 *
 * @author HaiYinLong
 * @version 2024/04/22 16:43
 **/
@Component
@RequiredArgsConstructor
@DS("mysql")
@Slf4j
@Configuration
public class DwBatchMapper<T, U> {
    private final SqlSessionFactory sqlSessionFactory;

    /**
     * Entity 类需要实现MapperEntity,判断是否修改还时插入
     *
     * @param data
     * @param uClass
     */

    public void batchInsert(List<T> data, Class<U> uClass) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            BaseMapper mapper = (BaseMapper)sqlSession.getMapper(uClass);
            int batchSize = 2000;
            int count = 0;

            for (T item : data) {
                if (item instanceof BaseEntity entity && !ObjectUtils.isEmpty(entity.getId())) {
                    mapper.updateById(entity);
                } else {
                    mapper.insert(item);
                }
                count++;

                if (count % batchSize == 0) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            // 提交剩余的数据
            if (count % batchSize != 0) {
                sqlSession.commit();
            }
        } catch (Exception e) {
            log.error(uClass.getSimpleName() + "批量插入数据失败", e);
            throw e;
        }
    }
}
