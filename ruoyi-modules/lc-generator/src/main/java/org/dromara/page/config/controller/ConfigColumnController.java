package org.dromara.page.config.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.page.config.domain.bo.ConfigColumnBo;
import org.dromara.page.config.domain.vo.ConfigColumnVo;
import org.dromara.page.config.service.IConfigColumnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库相关表字段 Controller
 *
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/configColumn")
public class ConfigColumnController extends BaseController {

    private final IConfigColumnService configColumnService;

    @SaCheckPermission("configColumn:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigColumnVo> list(ConfigColumnBo bo, PageQuery pageQuery) {
        return configColumnService.selectPageList(bo, pageQuery);
    }

    @SaCheckPermission("configColumn:query")
    @GetMapping(value = "/{id}")
    public R<ConfigColumnVo> getInfo(@PathVariable Long id) {
        return R.ok(configColumnService.selectById(id));
    }

    @SaCheckPermission("configColumn:add")
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigColumnBo bo) {
        return toAjax(configColumnService.insert(bo));
    }

    @SaCheckPermission("configColumn:edit")
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigColumnBo bo) {
        return toAjax(configColumnService.update(bo));
    }

    @SaCheckPermission("configColumn:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configColumnService.deleteByIds(ids));
    }
}

