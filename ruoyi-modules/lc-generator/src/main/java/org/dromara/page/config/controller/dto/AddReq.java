package org.dromara.page.config.controller.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author liguoxian
 */
@Data
public class AddReq {

    private String key;

    private Map<String, String> params;
}
