package org.dromara.page.config.mapper;

import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import org.dromara.page.config.domain.ConfigPage;
import org.dromara.page.config.domain.vo.ConfigPageVo;

/**
 * 页面配置 数据层
 *
 * @author liguoxian
 */
public interface ConfigPageMapper extends BaseMapperPlus<ConfigPage, ConfigPageVo> {

    default ConfigPage getByKey(String key) {
        return selectOne(new LambdaQueryWrapperX<ConfigPage>().eq(ConfigPage::getKey, key));
    }
}

