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
    private Socket client;
    private OutputStream os;
    private String statusCode;
    private StringBuilder headerSB;
    private StringBuilder contentSB;
    private static final String BLANK = " ";
    private static final String CRLF = "\r\n";
    private int length;

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
     * 添加正文内容
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

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        try {
            bw.write(this.headerSB.toString() + this.contentSB.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createHeaderInfo() {
        // 初始化响应头
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
