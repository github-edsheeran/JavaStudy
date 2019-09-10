package SXT._7ManualServer.part2;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装响应内容
 * @author: LiuDongMan
 * @createdDate: 2019-09-05 08:44
 **/
public class Response {
    private Socket client;  // 客户端
    private OutputStream os;    // 输出流
    private String statusCode;  // 响应状态码
    private StringBuilder headerSB; // 拼接响应头信息
    private StringBuilder contentSB;    // 拼接响应体信息
    private static final String BLANK = " ";    // 空格
    private static final String CRLF = "\r\n";  // 换行符
    private int length; // 响应体内容的长度

    /**
     * 初始化
     * @param client
     * @param statusCode
     */
    public Response(Socket client, String statusCode) {
        this.client = client;

        try {
            this.os = new BufferedOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.statusCode = statusCode;
        this.contentSB = new StringBuilder();
    }

    /**
     * 添加正文内容，如果涉及到输入内容要换行的话，例如，println方法，还需要加上CRLF换行字符串的长度
     * @param content
     * @return
     */
    public Response print(String content) {
        this.contentSB.append(content);
        this.length += content.getBytes().length;   // 每次给正文添加内容，则把内容的字节长度和成员变量累加
        return this;
    }

    /**
     * 将响应头和响应内容推送给客户端
     */
    public void pushToClient() {
        createHeaderInfo();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.os));
        try {
            bw.write(this.headerSB.toString() + this.contentSB.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化响应头信息，这个地方的响应状态码可以通过形参的形式传进来，更加灵活
     */
    private void createHeaderInfo() {
        this.headerSB = new StringBuilder().append("HTTP/1.1").append(BLANK).append(statusCode).append(BLANK);

        switch (statusCode) {
            case "200":
                this.headerSB.append("OK").append(CRLF);
                break;
            case "404":
                this.headerSB.append("NOT FOUND").append(CRLF);
                break;
            case "505":
                this.headerSB.append("SERVER ERROR").append(CRLF);
                break;
        }

        this.headerSB.append("Date:").append(new Date()).append(CRLF);
        this.headerSB.append("Server:DayNight Server/0.0.1;charset=UTF-8").append(CRLF);
        this.headerSB.append("Content-type:text/html").append(CRLF);
        this.headerSB.append("Content-length:").append(this.length).append(CRLF).append(CRLF); // 请求正文与请求头之间有个空行
    }

    public String getStatusCode() {
        return statusCode;
    }

    public StringBuilder getHeaderSB() {
        return headerSB;
    }

    public StringBuilder getContentSB() {
        return contentSB;
    }
}
