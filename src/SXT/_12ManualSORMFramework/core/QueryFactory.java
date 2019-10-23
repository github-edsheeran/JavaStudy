package SXT._12ManualSORMFramework.core;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 创建Query对象的工厂类
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:51
 **/
public class QueryFactory {
    private static Query prototypeObj;  // 原型对象

    /**
     * 私有化构造器
     */
    private QueryFactory() {
    }

    static {
        try {
            Class aClass = Class.forName(DBManager.getConf().getQueryClass());    // 加载指定的Query类
            prototypeObj = (Query) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static Query createQuery() {
        try {
            return (Query) prototypeObj.clone();    // 利用克隆模式提高效率
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
