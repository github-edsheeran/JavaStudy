package SXT._12ManualSORMFramework.bean;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 管理配置信息
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 16:02
 **/
public class Configuration {
    /**
     * 驱动类
     */
    private String driver;
    /**
     * JDBC的URL
     */
    private String url;
    /**
     * 数据库的用户名
     */
    private String user;
    /**
     * 数据库的密码
     */
    private String password;
    /**
     * 正在使用的数据库
     */
    private String usingDB;
    /**
     * 项目的源码路径
     */
    private String srcPath;
    /**
     * 扫描生成java类的包(po:persistence object)
     */
    private String poPackage;

    public Configuration(String driver, String url, String user, String password, String usingDB, String srcPath, String poPackage) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.usingDB = usingDB;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
    }

    public Configuration() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
}
