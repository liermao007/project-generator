package org.dromara.page.config.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.page.config.controller.dto.*;
import org.dromara.page.config.domain.ConfigColumn;
import org.dromara.page.config.domain.ConfigPage;
import org.dromara.page.config.domain.ConfigTable;
import org.dromara.page.config.domain.ConfigTableRelevance;
import org.dromara.page.config.mapper.ConfigColumnMapper;
import org.dromara.page.config.mapper.ConfigPageMapper;
import org.dromara.page.config.mapper.ConfigTableMapper;
import org.dromara.page.config.mapper.ConfigTableRelevanceMapper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据表工具类
 * @author liguoxian
 */
public class TableUtils {

    public static final String TABLE_SCHEMA = "ry-vue";
    private static ConfigTableMapper tableMapper = SpringUtils.getBean(ConfigTableMapper.class);
    private static ConfigColumnMapper columnMapper = SpringUtils.getBean(ConfigColumnMapper.class);
    private static ConfigTableRelevanceMapper tableRelevanceMapper = SpringUtils.getBean(ConfigTableRelevanceMapper.class);
    private static ConfigPageMapper pageMapper = SpringUtils.getBean(ConfigPageMapper.class);

    private static Table<String, String, ConfigTableRelevance> relevanceColumn = HashBasedTable.create();
    private static Table<String, String, ConfigTableRelevance> relevanceTable = HashBasedTable.create();
    private static Map<String, List<QueryColumn>> queryColumnMap = new HashMap<>();

    static {
        List<ConfigTableRelevance> tableRelevances = tableRelevanceMapper.selectList(TABLE_SCHEMA);
        for (ConfigTableRelevance tableRelevance : tableRelevances) {
            relevanceColumn.put(tableRelevance.getSourceTable(), tableRelevance.getSourceColumn(), tableRelevance);

            ConfigTableRelevance reverse = new ConfigTableRelevance(tableRelevance.getLinkTable(), tableRelevance.getLinkColumn(), tableRelevance.getSourceTable(), tableRelevance.getSourceColumn());
            relevanceColumn.put(tableRelevance.getLinkTable(), tableRelevance.getLinkColumn(), reverse);

            relevanceTable.put(tableRelevance.getSourceTable(), tableRelevance.getLinkTable(), tableRelevance);
            relevanceTable.put(tableRelevance.getLinkTable(), tableRelevance.getSourceTable(), reverse);
        }
    }

    /**
     * 获取对应数据库的表信息
     * @param tableName 表名称/表注释
     * @return
     */
    public static List<ConfigTable> getTables(String tableName) {
        return getTables(TABLE_SCHEMA, tableName);
    }

    public static List<ConfigTable> getTables(String tableSchema, String tableName) {
        return tableMapper.selectList(tableSchema, tableName);
    }

    /**
     * 获取数据表的表字段
     * @param tableName 表名称
     * @return
     */
    public static List<ConfigColumn> getTableColumns(String tableName) {
        return columnMapper.selectList(TABLE_SCHEMA, tableName);
    }

    public static ConfigPage getConfig(String key) {
        return pageMapper.getByKey(key);
    }

    public static void add(String key, Map<String, String> params) {

    }

    /*public static void saveByJson(AddReq data) {
        JSON json = JSONUtil.parse(data.getInfo());
        String mod = json.getByPath("mod", String.class);
        String key = json.getByPath("key", String.class);
        Map dataMap = json.getByPath("datas", Map.class);

        Table<String, String, Object> savaData = HashBasedTable.create();

        Collection<Map> elements = data.getParams().values();
        for (Map element : elements) {
            String tableName = element.get("table") != null ? element.get("table").toString() : "";
            String columnName = element.get("column") != null ? element.get("column").toString() : "";
            savaData.put(tableName, columnName, element.get("data"));
        }
        Set<String> tableSet = savaData.rowKeySet();

        if("u".equals(mod)) {
            List<UpdateColumns> updateColumns = getUpdateColumns(key);
            Map<String, String> alias = updateColumns.stream().collect(Collectors.toMap(UpdateColumns::getT, UpdateColumns::getA, (oldValue, newValue) -> oldValue));
            for (String tableName : tableSet) {
                String primaryKey = "id";
                Long primaryValue = data.getIds().get(alias.get(tableName));
                savaData.put(tableName, primaryKey, primaryValue);
            }
        } else {
            for (String tableName : tableSet) {
                String primaryKey = "id";
                Long primaryValue = IdUtil.getSnowflakeNextId();
                savaData.put(tableName, primaryKey, primaryValue);
            }
        }

        for (String tableName : tableSet) {
            List<ConfigColumn> noValueColumn = getNoValueColumn(tableName, savaData.row(tableName));
            for (ConfigColumn column : noValueColumn) {
                ConfigTableRelevance tableRelevance = relevanceColumn.get(column.getTableName(), column.getColumnName());
                Object linkData = null;
                if(tableRelevance != null) {
                    linkData = savaData.get(tableRelevance.getLinkTable(), tableRelevance.getLinkColumn());
                }
                if(linkData != null) {
                    savaData.put(tableName, column.getColumnName(), linkData);
                } else {
                    throw new IllegalArgumentException(column.getColumnComment() + "不能为空");
                }
            }
            if("u".equals(mod)) {
                String insertSql = getUpdateSql(tableName, savaData.row(tableName));
                tableMapper.executeUpdateSql(insertSql);
            } else {
                String insertSql = getInsertSql(tableName, savaData.row(tableName));
                tableMapper.executeInsertSql(insertSql);
            }

        }
    }*/

    public static String getInsertSql(String tableName, Map<String, Object> data) {
        StringBuilder sb = new StringBuilder("insert into ").append(tableName);

        StringJoiner fieldJoiner = new StringJoiner(",", "(", ")");
        StringJoiner valueJoiner = new StringJoiner(",", "(", ")");
        data.keySet().forEach(field -> {
            fieldJoiner.add(field);
            valueJoiner.add("'" + data.get(field).toString() + "'");
        });
        return sb.append(fieldJoiner).append(" values ").append(valueJoiner).toString();
    }

    public static String getUpdateSql(String tableName, Map<String, Object> data) {
        StringBuilder sb = new StringBuilder("update ").append(tableName);

        StringJoiner updateJoiner = new StringJoiner(",");
        Long id = Long.parseLong(data.get("id").toString());
        data.keySet().stream().filter(key -> !"id".equals(key)).forEach(field -> {
            updateJoiner.add(field + "=" + "'" + data.get(field).toString() + "'");
        });
        return sb.append(" set ").append(updateJoiner).append(" where id = ").append(id).toString();
    }

    public static List<ConfigColumn> getNoValueColumn(String tableName, Map<String, Object> data) {
        List<ConfigColumn> tableColumns = getTableColumns(tableName);
        return tableColumns.stream()
                .filter(column -> column.getIsNullable().equalsIgnoreCase("NO"))
                .filter(column -> !data.containsKey(column.getColumnName()))
                .collect(Collectors.toList());
    }

    public static String getSelectSql(String key, Map<String, String> params) {
        ConfigPage configPage = pageMapper.getByKey(key);
        Config data = JSONUtil.toBean(configPage.getConfigData(), Config.class);

        StringJoiner fromJoiner = new StringJoiner(" ");
        List<QueryTable> tables = data.getTables();
        Map<String, QueryTable> tableMap = new HashMap<>();
        for (int i = 1; i <= tables.size(); i++) {
            QueryTable queryTable = tables.get(i - 1);
            queryTable.setA("t" + i);
            tableMap.put(queryTable.getT(), queryTable);
            StringBuilder sb = new StringBuilder();
            if(i != 1) {
                sb.append(queryTable.getJ() != null ? queryTable.getJ() : "")
                        .append(" ");
            }
            sb.append(queryTable.getT())
                    .append(" ")
                    .append(queryTable.getA());
            if(i != 1) {
                ConfigTableRelevance tableRelevance = relevanceTable.get(queryTable.getT(), queryTable.getL());
                sb.append(" ON ")
                        .append(queryTable.getA())
                        .append(".")
                        .append(tableRelevance.getSourceColumn())
                        .append(" = ")
                        .append(tableMap.get(queryTable.getL()).getA())
                        .append(".")
                        .append(tableRelevance.getLinkColumn());
            }
            fromJoiner.add(sb.toString());
        }

        StringJoiner whereJoiner = new StringJoiner(" AND ");
        for (QueryParam param : data.getParams()) {
            QueryTable queryTable = tableMap.get(param.getT());
            String value = params.get(param.getField());
            if(StrUtil.isNotBlank(value)) {
                whereJoiner.add(queryTable.getA() + "." + param.getC() + " " + param.getE() + " '" + value + "'");
            }
        }

        StringJoiner columnJoiner = new StringJoiner(",");
        List<QueryColumn> queryColumns = new ArrayList<QueryColumn>();
        data.getColumns().stream()
                .filter(column -> column.getT() != null && column.getC() != null)
                .forEach(column -> {
                    QueryTable queryTable = tableMap.get(column.getT());
                    columnJoiner.add(queryTable.getA() + "." + column.getC() + " AS '" + column.getProp() + "'");
                    queryColumns.add(column);
                });

        queryColumnMap.put(key, queryColumns);
        String sql = "SELECT " + columnJoiner + " FROM " + fromJoiner + (whereJoiner.length() > 0 ? " WHERE " + whereJoiner : "");

        return sql;
    }

    public static QueryColumn getQueryColumnByProp(String key, String prop) {
        List<QueryColumn> queryColumns = queryColumnMap.get(key);
        if(queryColumns != null) {
            return queryColumns.stream().filter(column -> prop.equals(column.getProp())).findFirst().get();
        }
        return null;
    }

    public static List<UpdateColumns> getUpdateColumns(String key) {
        ConfigPage config = getConfig(key);
        UpdateConfig updateConfig = JSONUtil.toBean(config.getConfigData(), UpdateConfig.class);
        Map<String, String> alias = new HashMap<>();
        int i = 1;
        for (UpdateColumns column : updateConfig.getElements()) {
            if(!alias.containsKey(column.getT())) {
                column.setA("t" + i++);
                alias.put(column.getT(), column.getA());
            } else {
                column.setA(alias.get(column.getT()));
            }
        }
        return updateConfig.getElements();
    }

    public static ConfigTableRelevance getRelevanceTable(String t1, String t2) {
        return relevanceTable.get(t1, t2);
    }
}
