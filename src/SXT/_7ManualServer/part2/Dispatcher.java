package SXT._7ManualServer.part2;

import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 分发器
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 15:36
 **/
public class Dispatcher implements Runnable {
    private Socket client;
    private Request request;
    private Response response;

    public Dispatcher(Socket client) {
        this.client = client;
        this.request = new Request(client);
        this.response = new Response(client, "200");
    }

    @Override
    public void run() {
        this.request.parseRequestInfo();

        Servlet servlet = WebApp.getServletFromURL(request.getPath());

        if (servlet != null) {
            servlet.service(this.request, this.response);
        } else {
            // ...返回错误页面或者相关信息
        }

        this.response.pushToClient();

        // 为了防止出现阻塞的情况，可以在这个地方手动关闭client，实现客户端与服务端的短连接
    }
}
