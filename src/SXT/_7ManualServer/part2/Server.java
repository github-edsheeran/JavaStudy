package SXT._7ManualServer.part2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 手动实现一个服务器
 * @author: LiuDongMan
 * @createdDate: 2019-09-04 15:06
 **/
public class Server {
    private ServerSocket server;

    public void start() {
        try {
            this.server = new ServerSocket(7777);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败......");
        }
    }

    public void receive() {
//        try {
//            Socket client = this.server.accept();
//            InputStream is = client.getInputStream();
//            byte[] flush01 = new byte[1024 * 1024];
//            try {
//                int length = is.read(flush01);
//                System.out.println(new String(flush01, 0, length));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("客户端错误......");
//        }

        try {
            Socket client = this.server.accept();
            Request request = new Request(client);
            request.parseRequestInfo();
            System.out.println("请求方法: " + request.getMethod());
            System.out.println("请求URL: " + request.getUrl());
            System.out.println("请求参数: " + request.getRequestParameters());

            Response response = new Response(client, "200");
            response.print("<html>");
            response.print("<head>");
            response.print("<title>");
            response.print("服务器响应成功");
            response.print("</title>");
            response.print("</head>");
            response.print("<body>");
            response.print("你好，世界！");
            response.print("</body>");
            response.print("</html>");

            response.pushToClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {

    }

    public void stop() {

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
