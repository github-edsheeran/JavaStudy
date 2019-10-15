package SXT._12ManualSORMFramework.bean;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装java属性以及对应的get和set方法
 * @author: LiuDongMan
 * @createdDate: 2019-10-15 09:28
 **/
public class JavaFieldGetSet {
    /**
     * 属性的源码信息，例如：private String userName;
     */
    private String fieldInfo;

    /**
     * 属性get方法的信息，例如：public String getUserName(){return userName;}
     */
    private String getInfo;

    @Override
    public String toString() {
        System.out.println(fieldInfo);
        System.out.println(getInfo);
        System.out.println(setInfo);

        return super.toString();
    }

    /**
     * 属性set方法的信息，例如：public void setUserName(String userName) {this.userName = userName;}
     */
    private String setInfo;

    public JavaFieldGetSet() {
    }

    public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }
}
