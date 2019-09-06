package SXT._7ManualServer.part2;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: web.xml中servlet-mapping对应的实体类
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 14:27
 **/
public class ServletMapping {
    private String servletName;
    private Set<String> urlPatterns;

    public ServletMapping() {
        this.urlPatterns = new HashSet<>();
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public Set<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void addUrlPatterns(String urlPattern) {
        this.urlPatterns.add(urlPattern);
    }
}
