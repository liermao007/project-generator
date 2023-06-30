package org.dromara.page.config.controller.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author liguoxian
 */
@Data
public class EditReq {

    private Long id;

    private String key;

    private Map<String, String> params;
}
