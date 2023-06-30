package org.dromara.page.config.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 页面配置
 *
 * @author liguoxian
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("config_page")
public class ConfigPage extends TenantEntity {


    @TableId
    private Long id;

    /**
     * 标识码
     */
    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型，1列表，2表单
     */
    private String type;

    /**
     * 配置
     */
    private String configData;

}

