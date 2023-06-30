package org.dromara.page.config.controller;

import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.page.config.controller.dto.AddReq;
import org.dromara.page.config.domain.vo.ConfigTableVo;
import org.dromara.page.config.service.ICrudService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author liguoxian
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/crud")
public class CommonController extends BaseController {

    private ICrudService crudService;

    @PostMapping("add")
    public R<Void> add(@RequestBody AddReq addReq) {

        return null;
    }

    @PutMapping("edit")
    public R<Void> edit() {
        return null;
    }

    @DeleteMapping("delete")
    public R<Void> delete() {
        return null;
    }

    @GetMapping("list")
    public R<Void> list(Map<String, String> params) {
        return null;
    }

    @GetMapping("/page")
    public TableDataInfo<ConfigTableVo> page(Map<String, String> params, PageQuery pageQuery) {
        return null;
    }
}
