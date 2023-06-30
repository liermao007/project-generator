package org.dromara.page.config.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.page.config.domain.bo.ConfigTableBo;
import org.dromara.page.config.domain.vo.ConfigTableVo;
import org.dromara.page.config.service.IConfigTableService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库相关表 Controller
 *
 * @author liguoxian
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/configTable")
public class ConfigTableController extends BaseController {

    private final IConfigTableService configTableService;

    @SaCheckPermission("configTable:list")
    @GetMapping("/list")
    public TableDataInfo<ConfigTableVo> list(ConfigTableBo bo, PageQuery pageQuery) {
        return configTableService.selectPageList(bo, pageQuery);
    }

    @SaCheckPermission("configTable:query")
    @GetMapping(value = "/{id}")
    public R<ConfigTableVo> getInfo(@PathVariable Long id) {
        return R.ok(configTableService.selectById(id));
    }

    @SaCheckPermission("configTable:add")
    @PostMapping
    public R<Void> add(@Validated @RequestBody ConfigTableBo bo) {
        return toAjax(configTableService.insert(bo));
    }

    @SaCheckPermission("configTable:edit")
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ConfigTableBo bo) {
        return toAjax(configTableService.update(bo));
    }

    @SaCheckPermission("configTable:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(configTableService.deleteByIds(ids));
    }
}

