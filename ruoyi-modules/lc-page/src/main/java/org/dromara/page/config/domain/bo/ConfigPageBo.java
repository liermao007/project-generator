package org.dromara.page.config.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.page.config.domain.ConfigPage;

/**
 * 页面配置 视图对象
 *
 * @author liguoxian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ConfigPage.class, reverseConvertGenerate = false)
public class ConfigPageBo extends BaseEntity {


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

