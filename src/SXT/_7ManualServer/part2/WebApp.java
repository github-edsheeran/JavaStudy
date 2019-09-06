package SXT._7ManualServer.part2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 解析web.xml
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 14:18
 **/
public class WebApp {
    private static WebContext webContext;

    static {
        try {
            // 1.获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 2.从解析工厂获取解析器
            SAXParser parser = factory.newSAXParser();
            // 3.编写处理器
            // 4.加载文档Document注册处理器
            WebHandler webHandler = new WebHandler();
            // 5.解析
            parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("SXT/_7ManualServer/part2/web.xml"), webHandler);
            // 6.获取XML文件中存储的数据
            List<ServletBean> servletList = webHandler.getServletList();
            webContext = new WebContext(webHandler.getServletList(), webHandler.getServletMappingList());
        } catch (Exception e) { // static块不能抛出异常，一次性全部接收
            e.printStackTrace();
        }
    }

    /**
     * 根据web.xml中的url反射出对应的对象，不用自己手动生成
     * @param url
     * @return
     */
    public static Servlet getServletFromURL(String url) {
        String className = webContext.getClassByURL(url);

        try {
            Class aClass = Class.forName(className);
            Servlet servlet = (Servlet) aClass.newInstance();

            return servlet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
