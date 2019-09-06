package SXT._7ManualServer.part2;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: web.xml中servlet对应的实体类
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 14:25
 **/
public class ServletBean {
    private String servletName;
    private String servletClass;

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }
}
