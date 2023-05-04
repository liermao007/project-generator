package org.dromara.page.config.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.page.config.domain.ConfigTable;

/**
 * 数据库相关表 视图对象
 *
 * @author liguoxian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigTable.class, reverseConvertGenerate = false)
public class ConfigTableBo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 所属数据库
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

}

