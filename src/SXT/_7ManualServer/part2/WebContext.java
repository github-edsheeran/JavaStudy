package SXT._7ManualServer.part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: web.xml中对servlet和servlet-mapping进行封装的实体类
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 14:38
 **/
public class WebContext {
    private List<ServletBean> servletList;
    private List<ServletMapping> servletMappingList;
    private Map<String, String> servletMap = new HashMap<>();
    private Map<String, String> servletMappingMap = new HashMap<>();

    public WebContext() {
    }

    public WebContext(List<ServletBean> servletList, List<ServletMapping> servletMappingList) {
        this.servletList = servletList;
        this.servletMappingList = servletMappingList;

        for (ServletBean bean : this.servletList) {
            this.servletMap.put(bean.getServletName(), bean.getServletClass());
        }

        for (ServletMapping bean : this.servletMappingList) {
            for (String pattern : bean.getUrlPatterns()) {
                this.servletMappingMap.put(pattern, bean.getServletName());
            }
        }
    }

    public String getClassByURL(String url) {
        return this.servletMap.get(this.servletMappingMap.get(url));
    }
}