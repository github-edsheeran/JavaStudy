package SXT._7ManualServer.part2;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 加入LoginServlet
 * @author: LiuDongMan
 * @createdDate: 2019-09-06 11:00
 **/
public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("Success");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("Hello, world! Login Success!");
        response.print("</body>");
        response.print("</html>");
    }
}
