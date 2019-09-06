package SXT._7ManualServer.part2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: web.xml对应的处理器
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 14:23
 **/
public class WebHandler extends DefaultHandler {
    private List<ServletBean> servletList;  // 类似于容器这种成员变量一定要记得初始化，否则在使用的时候会出现空指针异常
    private List<ServletMapping> servletMappingList;
    private String currentTag;
    private boolean isMapping;
    private ServletBean servlet;
    private ServletMapping servletMapping;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null && !"".equals(qName)) {
            if ("servlet".equals(qName)) {
                this.servlet = new ServletBean();
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

    public List<ServletBean> getServletList() {
        return servletList;
    }

    public List<ServletMapping> getServletMappingList() {
        return servletMappingList;
    }
}

