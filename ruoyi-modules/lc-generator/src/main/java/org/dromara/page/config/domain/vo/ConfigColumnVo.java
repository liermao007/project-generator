package org.dromara.page.config.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.page.config.domain.ConfigColumn;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表字段 视图对象
 *
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigColumn.class)
public class ConfigColumnVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "主键")
    private Long id;

    @ExcelProperty(value = "所属数据库")
    private String tableSchema;

    @ExcelProperty(value = "表名")
    private String tableName;

    @ExcelProperty(value = "数据列名")
    private String columnName;

    @ExcelProperty(value = "数据列描述")
    private String columnComment;

    @ExcelProperty(value = "键类型")
    private String columnKey;

    @ExcelProperty(value = "数据类型")
    private String dataType;

    @ExcelProperty(value = "是否允许为空")
    private String isNullable;

}

