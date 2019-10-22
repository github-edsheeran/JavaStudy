package SXT._12ManualSORMFramework.core;

import SXT._12ManualSORMFramework.bean.ColumnInfo;
import SXT._12ManualSORMFramework.bean.TableInfo;
import SXT._12ManualSORMFramework.po.Emp;
import SXT._12ManualSORMFramework.utils.JDBCUtils;
import SXT._12ManualSORMFramework.utils.ReflectUtils;
import SXT._12ManualSORMFramework.vo.EmpVO;
import SXT._8AnnotationAndReflection.part2.Table;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责针对mySQL数据库的相关操作，通用的数据库方法转移到抽象类Query中
 * @author: LiuDongMan
 * @createdDate: 2019-10-16 09:49
 **/
public class MySQLQuery extends Query {

}
