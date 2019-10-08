package SXT._12ManualSORMFramework.bean;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装表中一个字段的信息
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:59
 **/
public class ColumnInfo {
    /**
     * 字段名
     */
    private String name;

    /**
     * 字段数据类型
     */
    private String dataType;

    /**
     * 字段的键的类型(0:普通键;1:主键;2:外键)
     */
    private int keyType;

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public ColumnInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }
}
