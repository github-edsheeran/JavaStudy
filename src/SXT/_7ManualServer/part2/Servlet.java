package SXT._7ManualServer.part2;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 加入Servlet，进一步封装代码
 * @author: LiuDongMan
 * @createdDate: 2019-09-05 08:44
 **/
public interface Servlet {
    void service(Request request, Response response);
}
