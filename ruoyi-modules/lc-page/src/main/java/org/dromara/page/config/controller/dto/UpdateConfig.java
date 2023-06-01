package org.dromara.page.config.controller.dto;

import java.util.List;

/**
 * @author liguoxian
 */
public class UpdateConfig {

    private List<UpdateColumns> elements;

    public List<UpdateColumns> getElements() {
        return elements;
    }

    public void setElements(List<UpdateColumns> elements) {
        this.elements = elements;
    }
}
