package org.dromara.page.config.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 数据库相关表关联信息
 *
 * @author liguoxian
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("config_table_relevance")
public class ConfigTableRelevance extends TenantEntity {

    /**
     * 主键
     */
    @TableId
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

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    public ConfigTableRelevance(String sourceTable, String sourceColumn, String linkTable, String linkColumn) {
        this.sourceTable = sourceTable;
        this.sourceColumn = sourceColumn;
        this.linkTable = linkTable;
        this.linkColumn = linkColumn;
    }
}

