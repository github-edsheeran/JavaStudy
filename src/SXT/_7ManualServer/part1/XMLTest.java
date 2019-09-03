package SXT._7ManualServer.part1;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

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
    }
}

class PersonHandler extends DefaultHandler {

}