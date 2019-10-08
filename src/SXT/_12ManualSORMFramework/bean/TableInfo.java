package SXT._12ManualSORMFramework.bean;

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

    public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onlyPrimaryKey) {
        this.name = name;
        this.columns = columns;
        this.onlyPrimaryKey = onlyPrimaryKey;
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
