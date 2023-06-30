package org.dromara.page.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.mapper.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.BaseServiceImpl;
import org.dromara.page.config.domain.ConfigPage;
import org.dromara.page.config.domain.bo.ConfigPageBo;
import org.dromara.page.config.domain.vo.ConfigPageVo;
import org.dromara.page.config.mapper.ConfigPageMapper;
import org.dromara.page.config.service.IConfigPageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 页面配置 服务实现
 *
 * @author liguoxian
 */
@RequiredArgsConstructor
@Service
public class ConfigPageServiceImpl extends BaseServiceImpl<ConfigPageMapper, ConfigPage, ConfigPageVo, ConfigPageBo> implements IConfigPageService {

    @Override
    public TableDataInfo<ConfigPageVo> selectPageList(ConfigPageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ConfigPage> lqw = buildQueryWrapper(bo);
        Page<ConfigPageVo> page = mapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    @Override
    public List<ConfigPageVo> selectList(ConfigPageBo bo) {
        LambdaQueryWrapper<ConfigPage> lqw = buildQueryWrapper(bo);
        return mapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ConfigPage> buildQueryWrapper(ConfigPageBo bo) {
        return new LambdaQueryWrapperX<ConfigPage>()
                .eqIfPresent(ConfigPage::getKey, bo.getKey())
                .eqIfPresent(ConfigPage::getName, bo.getName())
                .eqIfPresent(ConfigPage::getType, bo.getType())
                .eqIfPresent(ConfigPage::getConfigData, bo.getConfigData())
        ;
    }
}
