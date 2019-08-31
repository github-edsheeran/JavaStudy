package SXT._6NetworkProgram.part3;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 09:00
 **/
public class TeacherReceive {
    public static void main(String[] args) {
        new Thread(new UDPTalkThreadServer(9999, "学生")).start();
        new Thread(new UDPTalkThreadClient(5555, "localhost", 8888)).start();
    }
}
