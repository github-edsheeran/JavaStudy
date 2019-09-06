package SXT._7ManualServer.part2;

import java.io.IOException;
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
    private boolean runningFlag;

    public void start() {
        try {
            this.server = new ServerSocket(7777);
            this.runningFlag = true;

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

        while (this.runningFlag) {
            try {
                Socket client = this.server.accept();

                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Socket client = this.server.accept();
//            Request request = new Request(client);
//            request.parseRequestInfo();
////            System.out.println("请求方法: " + request.getMethod());
////            System.out.println("请求URL: " + request.getUrl());
////            System.out.println("请求参数: " + request.getParameters());
////            System.out.println("请求路径: " + request.getPath());
//
//            // 为什么这个地方的响应信息是中文的话会出现乱码？
//            Response response = new Response(client, "200");
//            Servlet servlet = null;
//
//            /**
//             * 根据不同的请求路径生成不同的Servlet对象
//             * 可以把请求url中的请求路径分离开来，作为一个单独的成员变量，例如：/login，/register等
//             */
////            if (request.getUrl().indexOf("login") != -1) {
////                servlet = new LoginServlet();
////            } else if (request.getUrl().indexOf("register") != -1) {
////                servlet = new RegisterServlet();
////            } else {
////                // ......
////            }
//
//            // 现在使用反射来根据请求路径生成对应的servlet对象，不用再自己手动生成
//            servlet = WebApp.getServletFromURL(request.getPath());
//
//            if (servlet != null) {
//                servlet.service(request, response);
//            } else {
//                // 错误相关信息
//            }
//
//            response.pushToClient();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
