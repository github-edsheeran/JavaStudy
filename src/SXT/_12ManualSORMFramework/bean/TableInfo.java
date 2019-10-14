package SXT._12ManualSORMFramework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 存储表的结构信息
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 16:02
 **/
public class TableInfo {
    /**
     * 表名
     */
    private String name;

    /**
     * 所有字段
     */
    private Map<String, ColumnInfo> columns;

    /**
     * 唯一主键(目前只能处理表中只有一个主键的情况)
     */
    private ColumnInfo onlyPrimaryKey;

    /**
     * 如果有联合主键，则在这里存储
     */
    private List<ColumnInfo> primaryKeys;

    public List<ColumnInfo> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<ColumnInfo> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public TableInfo(String name, List<ColumnInfo> primaryKeys, Map<String, ColumnInfo> columns) {
        this.name = name;
        this.columns = columns;
        this.primaryKeys = primaryKeys;
    }

    public TableInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPrimaryKey() {
        return onlyPrimaryKey;
    }

    public void setOnlyPrimaryKey(ColumnInfo onlyPrimaryKey) {
        this.onlyPrimaryKey = onlyPrimaryKey;
    }
}
