package org.dromara.page.config.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.page.config.domain.ConfigTable;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据库相关表 视图对象
 *
 * @author liguoxian
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ConfigTable.class)
public class ConfigTableVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "主键")
    private Long id;

    @ExcelProperty(value = "所属数据库")
    private String tableSchema;

    @ExcelProperty(value = "表名")
    private String tableName;

    @ExcelProperty(value = "表描述")
    private String tableComment;

}

