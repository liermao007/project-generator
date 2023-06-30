package org.dromara.page.config.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.page.config.domain.bo.ConfigPageBo;
import org.dromara.page.config.domain.vo.ConfigPageVo;
import org.dromara.page.config.service.IConfigPageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 页面配置 Controller
 *
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/configPage")
public class ConfigPageController extends BaseController {

    private final IConfigPageService configPageService;

    @SaCheckPermission("configPage:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigPageVo> list(ConfigPageBo bo, PageQuery pageQuery) {
        return configPageService.selectPageList(bo, pageQuery);
    }

    @SaCheckPermission("configPage:query")
    @GetMapping(value = "/{id}")
    public R<ConfigPageVo> getInfo(@PathVariable Long id) {
        return R.ok(configPageService.selectById(id));
    }

    @SaCheckPermission("configPage:add")
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigPageBo bo) {
        return toAjax(configPageService.insert(bo));
    }

    @SaCheckPermission("configPage:edit")
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigPageBo bo) {
        return toAjax(configPageService.update(bo));
    }

    @SaCheckPermission("configPage:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configPageService.deleteByIds(ids));
    }
}

