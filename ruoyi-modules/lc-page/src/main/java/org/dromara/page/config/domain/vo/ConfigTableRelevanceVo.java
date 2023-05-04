package org.dromara.page.config.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.page.config.domain.ConfigTableRelevance;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表关联信息 视图对象
 *
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigTableRelevance.class)
public class ConfigTableRelevanceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "主键")
    private Long id;

    @ExcelProperty(value = "所属数据库")
    private String tableSchema;

    @ExcelProperty(value = "数据表")
    private String sourceTable;

    @ExcelProperty(value = "数据列")
    private String sourceColumn;

    @ExcelProperty(value = "关联表")
    private String linkTable;

    @ExcelProperty(value = "关联列")
    private String linkColumn;

    @ExcelProperty(value = "数据表与关联表对应关系")
    private String linkType;

}

