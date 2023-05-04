package org.dromara.page.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.BaseServiceImpl;
import org.dromara.page.config.domain.ConfigTableRelevance;
import org.dromara.page.config.domain.bo.ConfigTableRelevanceBo;
import org.dromara.page.config.domain.vo.ConfigTableRelevanceVo;
import org.dromara.page.config.mapper.ConfigTableRelevanceMapper;
import org.dromara.page.config.service.IConfigTableRelevanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库相关表关联信息 服务实现
 *
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigTableRelevanceServiceImpl extends BaseServiceImpl<ConfigTableRelevanceMapper, ConfigTableRelevance, ConfigTableRelevanceVo, ConfigTableRelevanceBo> implements IConfigTableRelevanceService {

    @Override
    public TableDataInfo<ConfigTableRelevanceVo> selectPageList(ConfigTableRelevanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigTableRelevance> lqw = buildQueryWrapper(bo);
        Page<ConfigTableRelevanceVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigTableRelevanceVo> selectList(ConfigTableRelevanceBo bo) {
        LambdaQueryWrapper<ConfigTableRelevance> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigTableRelevance> buildQueryWrapper(ConfigTableRelevanceBo bo) {
        return new LambdaQueryWrapperX<ConfigTableRelevance>()
                .eqIfPresent(ConfigTableRelevance::getTableSchema, bo.getTableSchema())
                .eqIfPresent(ConfigTableRelevance::getSourceTable, bo.getSourceTable())
                .eqIfPresent(ConfigTableRelevance::getSourceColumn, bo.getSourceColumn())
                .eqIfPresent(ConfigTableRelevance::getLinkTable, bo.getLinkTable())
                .eqIfPresent(ConfigTableRelevance::getLinkColumn, bo.getLinkColumn())
                .eqIfPresent(ConfigTableRelevance::getLinkType, bo.getLinkType())
                .eqIfPresent(ConfigTableRelevance::getCreateDept, bo.getCreateDept())
        ;
    }
}
