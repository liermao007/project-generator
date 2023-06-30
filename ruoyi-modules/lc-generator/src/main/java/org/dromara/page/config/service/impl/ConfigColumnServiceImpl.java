package org.dromara.page.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.BaseServiceImpl;
import org.dromara.page.config.domain.ConfigColumn;
import org.dromara.page.config.domain.bo.ConfigColumnBo;
import org.dromara.page.config.domain.vo.ConfigColumnVo;
import org.dromara.page.config.mapper.ConfigColumnMapper;
import org.dromara.page.config.service.IConfigColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库相关表字段 服务实现
 *
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigColumnServiceImpl extends BaseServiceImpl<ConfigColumnMapper, ConfigColumn, ConfigColumnVo, ConfigColumnBo> implements IConfigColumnService {

    @Override
    public TableDataInfo<ConfigColumnVo> selectPageList(ConfigColumnBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigColumn> lqw = buildQueryWrapper(bo);
        Page<ConfigColumnVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigColumnVo> selectList(ConfigColumnBo bo) {
        LambdaQueryWrapper<ConfigColumn> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigColumn> buildQueryWrapper(ConfigColumnBo bo) {
        return new LambdaQueryWrapperX<ConfigColumn>()
                .eqIfPresent(ConfigColumn::getTableSchema, bo.getTableSchema())
                .eqIfPresent(ConfigColumn::getTableName, bo.getTableName())
                .eqIfPresent(ConfigColumn::getColumnName, bo.getColumnName())
                .eqIfPresent(ConfigColumn::getColumnComment, bo.getColumnComment())
                .eqIfPresent(ConfigColumn::getColumnKey, bo.getColumnKey())
                .eqIfPresent(ConfigColumn::getDataType, bo.getDataType())
                .eqIfPresent(ConfigColumn::getIsNullable, bo.getIsNullable())
                .eqIfPresent(ConfigColumn::getCreateDept, bo.getCreateDept())
        ;
    }
}
