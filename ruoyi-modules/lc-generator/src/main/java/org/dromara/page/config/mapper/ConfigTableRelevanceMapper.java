package org.dromara.page.config.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import org.dromara.page.config.domain.ConfigTableRelevance;
import org.dromara.page.config.domain.vo.ConfigTableRelevanceVo;

import java.util.List;

/**
 * 数据库相关表关联信息 数据层
 *
 * @author liguoxian
 */
public interface ConfigTableRelevanceMapper extends BaseMapperPlus<ConfigTableRelevance, ConfigTableRelevanceVo> {

    default List<ConfigTableRelevance> selectList(String tableSchema) {
        return selectList(new LambdaQueryWrapperX<ConfigTableRelevance>().eq(ConfigTableRelevance::getTableSchema, tableSchema));
    }
}

