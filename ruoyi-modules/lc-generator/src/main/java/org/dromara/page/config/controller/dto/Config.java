package org.dromara.page.config.controller.dto;

import java.util.List;

/**
 * @author liguoxian
 */
public class Config {

    private List<QueryTable> tables;

    private List<QueryParam> params;

    private List<QueryColumn> columns;

    public List<QueryTable> getTables() {
        return tables;
    }

    public void setTables(List<QueryTable> tables) {
        this.tables = tables;
    }

    public List<QueryParam> getParams() {
        return params;
    }

    public void setParams(List<QueryParam> params) {
        this.params = params;
    }

    public List<QueryColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<QueryColumn> columns) {
        this.columns = columns;
    }
}
