package org.dromara.page.config.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import org.dromara.page.config.domain.ConfigColumn;
import org.dromara.page.config.domain.vo.ConfigColumnVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库相关表字段 数据层
 *
 * @author liguoxian
 */
public interface ConfigColumnMapper extends BaseMapperPlus<ConfigColumn, ConfigColumnVo> {

    default List<ConfigColumn> selectList(String tableSchema, String tableName) {
        return selectList(new LambdaQueryWrapperX<ConfigColumn>()
            .eq(ConfigColumn::getTableSchema, tableSchema)
            .eq(ConfigColumn::getTableName, tableName));
    }
}

