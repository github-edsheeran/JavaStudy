package SXT._12ManualSORMFramework.core;

import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责数据库的DML操作(对外提供服务的核心接口)
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:19
 **/
public interface Query {
    /**
     * 直接执行一个DML语句
     * @param sql SQL语句
     * @param params 对应的参数
     * @return 返回执行SQL语句后影响的记录行数
     */
    int executeDML(String sql, Object[] params);

    /**
     * 将一个对象存储到数据库中
     * @param obj 要存储的对象
     */
    void insert(Object obj);

    /**
     * 根据主键id删除Class类对象所对应的数据库表中的记录
     * @param aClass 数据库表所对应的Class类对象
     * @param id 主键的值
     */
    void delete(Class aClass, int id);

    /**
     * 删除对象所对应的数据库表中的记录
     * @param obj
     */
    void delete(Object obj);

    /**
     * 更新对象所对应的数据库表中的记录，并且只更新指定的字段
     * @param obj 需要更新的对象
     * @param fieldNames 指定的字段名
     * @return 返回执行SQL语句后影响的记录行数
     */
    int update(Object obj, String[] fieldNames);

    /**
     * 查询返回多条记录，并将每行记录封装到数据库表对应的Class生成的对象中
     * @param sql 查询语句
     * @param aClass 数据库表所对应的Class对象
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    List queryRows(String sql, Class aClass, Object[] params);

    /**
     * 查询返回一条记录，并将每行记录封装到数据库表对应的Class生成的对象中
     * @param sql 查询语句
     * @param aClass 数据库表所对应的Class对象
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    Object queryUniqueRow(String sql, Class aClass, Object[] params);

    /**
     * 查询返回一个值，即一行一列，并将该值返回
     * @param sql 查询语句
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    Object queryValue(String sql, Object[] params);

    /**
     * 查询返回一个数字，即一行一列，并将该值返回，由于Number是所有数字包装类的父类，因此，作为返回值的参数类型
     * @param sql 查询语句
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    Number queryNumber(String sql, Object[] params);
}
