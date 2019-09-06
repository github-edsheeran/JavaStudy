package SXT._7ManualServer.part2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装请求内容
 * @author: LiuDongMan
 * @createdDate: 2019-09-05 08:44
 **/
public class Request {
    private InputStream is; // 输入流
    private Socket client;  // 客户端
    private String method;  // 请求方法
    private String url; // 请求url
    private String parameters;   // 请求参数
    private static final String CRLF = "\r\n";  // 换行符
    private Map<String, List<String>> parameterMap; // 请求参数封装map
    private String path;    // 请求路径

    /**
     * 初始化
     * @param client
     */
    public Request(Socket client) {
        this.client = client;

        try {
            this.is = new BufferedInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parameterMap = new HashMap<>();
    }

    /**
     * 转换客户端的请求信息
     */
    public void parseRequestInfo() {
        // 获取客户端的请求信息
        String requestInfo = null;

        try {
            byte[] datas = new byte[1024 * 1024];
            int length = is.read(datas);
            requestInfo = new String(datas, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 分解字符串
        // 第一个斜杠的位置
        int firstSlash = requestInfo.indexOf("/");
        // 获取请求方法
        this.method = requestInfo.substring(0, firstSlash - 1);
        // 获取请求URL，并根据问号的位置判断是否附带了请求参数
        this.url = requestInfo.substring(firstSlash, requestInfo.indexOf("HTTP/")).trim();
        int questionMarkIndex = this.url.indexOf("?");
        //获取请求路径，需要注意没有问号的情况
        this.path = requestInfo.substring(firstSlash, requestInfo.indexOf("?"));
        // 获取请求参数，需要注意的是，GET请求方法会把请求参数附带到url中，而POST请求方法除了会将请求参数附带到url中，还会在请求体中附带
        if ("GET".equals(this.method)) {
            if (questionMarkIndex != -1) {
                this.parameters = this.url.substring(questionMarkIndex + 1);
            } else {
                this.parameters = "";
            }
        } else if ("POST".equals(this.method)) {
            StringBuilder requestParamSB = new StringBuilder();

            if (questionMarkIndex != -1) {
                requestParamSB.append(this.url.substring(questionMarkIndex + 1));

                // 获取请求体中的参数信息
                String requestBodyParam = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();

                if (requestBodyParam != null && !"".equals(requestBodyParam)) {
                    requestParamSB.append("&").append(requestParamSB);
                }
            } else {
                requestParamSB.append(requestInfo.substring(requestInfo.lastIndexOf(CRLF)));
            }

            this.parameters = requestParamSB.toString();
        }

        convertToMap();

        System.out.println(this.parameterMap.toString());
    }

    /**
     * 将请求参数封装成map
     */
    private void convertToMap() {
        if (this.parameters.indexOf(CRLF) != -1) {
            this.parameters = this.parameters.substring(this.parameters.indexOf(CRLF) + 3);
        }

        if (this.parameters != null && !"".equals(this.parameters)) {
            String[] parameterArray = this.parameters.split("&");

            for (String s : parameterArray) {
                List<String> tempList = new ArrayList<>();
                String[] tempArray = s.split("=");

                // 还可以通过map的containsKey方法判断是否有相关的值
                if (this.parameterMap.get(tempArray[0]) == null || this.parameterMap.get(tempArray[0]).size() == 0) {
                    tempList.add(tempArray[1]);
                    this.parameterMap.put(tempArray[0], tempList);
                } else {
                    this.parameterMap.get(tempArray[0]).add(tempArray[1]);
                }
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getParameters() {
        return parameters;
    }

    public String getPath() {
        return path;
    }
}
