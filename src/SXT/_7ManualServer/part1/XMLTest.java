package SXT._7ManualServer.part1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解XML中的SAX解析
 * @author: LiuDongMan
 * @createdDate: 2019-09-03 21:26
 */
public class XMLTest {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3.编写处理器
        // 4.加载文档Document注册处理器
        PersonHandler personHandler = new PersonHandler();
        // 5.解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("SXT/_7ManualServer/part1/persons.xml"), personHandler);
        // 6.获取XML文件中存储的数据
        List<Person> personList = personHandler.getPersonList();

        for (Person person : personList) {
            System.out.println(person.getName() + " --> " + person.getAge());
        }
    }
}

/**
 * 处理器类
 */
class PersonHandler extends DefaultHandler {
    private List<Person> personList;    // 通过容器存储多条信息
    private Person person;  // 通过对象存储相关数据
    private String currentTag;  // 存储正在遍历的当前标签

    public PersonHandler() {
        this.personList = new ArrayList<>();
    }

    /**
     * 文档开始
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {

    }

    /**
     * 遍历开始元素标签
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null && !"".equals(qName)) {
            if ("person".equals(qName)) {
                this.person = new Person();
            }
        }

        this.currentTag = qName;    // 当遍历到起始标签时，则给成员变量currentTag赋值，方便characters方法进行调用
    }

    /**
     * 遍历结束元素标签
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null && !"".equals(qName)) {
            if ("person".equals(qName)) {
                this.personList.add(this.person);
            }
        }

        // 由于遍历XML文件时，其中的换行和空格也会进行遍历，因此，为了防止出现标签存在，但是数据为空的情况，手动将当前遍历的标签赋值为空
        this.currentTag = null;
    }

    /**
     * 文档结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
    }

    public List<Person> getPersonList() {
        return personList;
    }

    /**
     * 遍历标签里面的文本内容
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 利用成员变量存储当前正在遍历的标签
        if (this.currentTag != null && !"".equals(this.currentTag)) {
            if ("name".equals(this.currentTag)) {
                this.person.setName(new String(ch, start, length));
            } else if ("age".equals(this.currentTag)) {
                this.person.setAge(Integer.valueOf(new String(ch, start, length)));
            }
        }
    }
}