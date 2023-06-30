package org.dromara.page.config.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import org.dromara.page.config.domain.ConfigTable;
import org.dromara.page.config.domain.vo.ConfigTableVo;

import java.util.List;

/**
 * 数据库相关表 数据层
 *
 * @author liguoxian
 */
public interface ConfigTableMapper extends BaseMapperPlus<ConfigTable, ConfigTableVo> {

    default List<ConfigTable> selectList(String tableSchema, String tableName) {
        return selectList(new LambdaQueryWrapperX<ConfigTable>()
            .eq(ConfigTable::getTableSchema, tableSchema)
            .like(ConfigTable::getTableName, tableName));
    }

    @Insert("${sql}")
    int executeInsertSql(String sql);

    @Update("${sql}")
    int executeUpdateSql(String sql);
}

