package org.dromara.page.config.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.page.config.domain.ConfigPage;

import java.io.Serial;
import java.io.Serializable;

/**
 * 页面配置 视图对象
 *
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigPage.class)
public class ConfigPageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "${column.comment}")
    private Long id;

    @ExcelProperty(value = "标识码")
    private String key;

    @ExcelProperty(value = "名称")
    private String name;

    @ExcelProperty(value = "类型，1列表，2表单")
    private String type;

    @ExcelProperty(value = "配置")
    private String configData;

}

