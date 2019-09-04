package SXT._7ManualServer.part1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解解析web.xml文件
 * @author: LiuDongMan
 * @createdDate: 2019-09-04 09:17
 **/
public class WebXMLTest {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3.编写处理器
        // 4.加载文档Document注册处理器
        WebHandler webHandler = new WebHandler();
        // 5.解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("SXT/_7ManualServer/part1/web.xml"), webHandler);
        // 6.获取XML文件中存储的数据
        List<Servlet> servletList = webHandler.getServletList();

        WebContext webContext = new WebContext(webHandler.getServletList(), webHandler.getServletMappingList());

//        for (Servlet servlet : servletList) {
//            System.out.println(servlet.getServletName() + " --> " + servlet.getServletClass());
//        }
//
//        List<ServletMapping> servletMappingList = webHandler.getServletMappingList();
//
//        for (ServletMapping servletMapping : servletMappingList) {
//            System.out.println(servletMapping.getServletName());
//            Set<String> urlPatterns = servletMapping.getUrlPatterns();
//
//            for (String urlPattern : urlPatterns) {
//                System.out.println("--> " + urlPattern);
//            }
//        }

        // 7.根据URL找到对应的类，然后利用反射生成对应的对象，假设网页输入的URL为：/g
        String className01 = webContext.getClassByURL("/g");
        String className02 = webContext.getClassByURL("/reg");
        Class aClass = Class.forName(className01);
        Class bClass = Class.forName(className02);
        LoginServlet loginServlet = (LoginServlet) aClass.newInstance();
        RegisterServlet registerServlet = (RegisterServlet) bClass.newInstance();
        loginServlet.service();
        registerServlet.service();
    }
}

class WebHandler extends DefaultHandler {
    private List<Servlet> servletList;  // 类似于容器这种成员变量一定要记得初始化，否则在使用的时候会出现空指针异常
    private List<ServletMapping> servletMappingList;
    private String currentTag;
    private boolean isMapping;
    private Servlet servlet;
    private ServletMapping servletMapping;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null && !"".equals(qName)) {
            if ("servlet".equals(qName)) {
                this.servlet = new Servlet();
                this.isMapping = false;
            } else if ("servlet-mapping".equals(qName)) {
                this.servletMapping = new ServletMapping();
                this.isMapping = true;
            }
        }

        this.currentTag = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.currentTag != null && !"".equals(this.currentTag)) {
            if (this.isMapping) {
                if ("servlet-name".equals(this.currentTag)) {
                    this.servletMapping.setServletName(new String(ch, start, length));
                } else if ("url-pattern".equals(this.currentTag)) {
                    this.servletMapping.addUrlPatterns(new String(ch, start, length));
                }
            } else {
                if ("servlet-name".equals(this.currentTag)) {
                    this.servlet.setServletName(new String(ch, start, length));
                } else if ("servlet-class".equals(this.currentTag)) {
                    this.servlet.setServletClass(new String(ch, start, length));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null && !"".equals(qName)) {
            if ("servlet".equals(qName)) {
                this.servletList.add(this.servlet);
            } else if ("servlet-mapping".equals(qName)) {
                this.servletMappingList.add(this.servletMapping);
            }
        }

        this.currentTag = null;
    }

    @Override
    public void endDocument() throws SAXException {

    }

    public WebHandler() {
        this.servletList = new ArrayList<>();
        this.servletMappingList = new ArrayList<>();
    }

    public List<Servlet> getServletList() {
        return servletList;
    }

    public List<ServletMapping> getServletMappingList() {
        return servletMappingList;
    }
}

class Servlet {
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

class ServletMapping {
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

class WebContext {
    private List<Servlet> servletList;
    private List<ServletMapping> servletMappingList;
    private Map<String, String> servletMap = new HashMap<>();
    private Map<String, String> servletMappingMap = new HashMap<>();

    public WebContext() {
    }

    public WebContext(List<Servlet> servletList, List<ServletMapping> servletMappingList) {
        this.servletList = servletList;
        this.servletMappingList = servletMappingList;

        for (Servlet bean : this.servletList) {
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

interface ServletInterface {
    void service();
}

class LoginServlet implements ServletInterface {

    @Override
    public void service() {
        System.out.println("LoginServlet");
    }
}

class RegisterServlet implements ServletInterface {

    @Override
    public void service() {
        System.out.println("RegisterServlet");
    }
}
