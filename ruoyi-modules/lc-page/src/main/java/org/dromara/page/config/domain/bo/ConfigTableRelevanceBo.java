package org.dromara.page.config.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.page.config.domain.ConfigTableRelevance;

/**
 * 数据库相关表关联信息 视图对象
 *
 * @author liguoxian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigTableRelevance.class, reverseConvertGenerate = false)
public class ConfigTableRelevanceBo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 所属数据库
     */
    private String tableSchema;

    /**
     * 数据表
     */
    private String sourceTable;

    /**
     * 数据列
     */
    private String sourceColumn;

    /**
     * 关联表
     */
    private String linkTable;

    /**
     * 关联列
     */
    private String linkColumn;

    /**
     * 数据表与关联表对应关系
     */
    private String linkType;

}

