package SXT._7ManualServer.part2;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 加入RegisterServlet
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 11:00
 **/
public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("Register success!");
    }
}
