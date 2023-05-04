package org.dromara.page.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.BaseServiceImpl;
import org.dromara.page.config.domain.ConfigTable;
import org.dromara.page.config.domain.bo.ConfigTableBo;
import org.dromara.page.config.domain.vo.ConfigTableVo;
import org.dromara.page.config.mapper.ConfigTableMapper;
import org.dromara.page.config.service.IConfigTableService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库相关表 服务实现
 *
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigTableServiceImpl extends BaseServiceImpl<ConfigTableMapper, ConfigTable, ConfigTableVo, ConfigTableBo> implements IConfigTableService {

    @Override
    public TableDataInfo<ConfigTableVo> selectPageList(ConfigTableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigTable> lqw = buildQueryWrapper(bo);
        Page<ConfigTableVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigTableVo> selectList(ConfigTableBo bo) {
        LambdaQueryWrapper<ConfigTable> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigTable> buildQueryWrapper(ConfigTableBo bo) {
        return new LambdaQueryWrapperX<ConfigTable>()
                .eqIfPresent(ConfigTable::getTableSchema, bo.getTableSchema())
                .eqIfPresent(ConfigTable::getTableName, bo.getTableName())
                .eqIfPresent(ConfigTable::getTableComment, bo.getTableComment())
                .eqIfPresent(ConfigTable::getCreateDept, bo.getCreateDept())
        ;
    }
}
