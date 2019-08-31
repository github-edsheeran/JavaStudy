package SXT._6NetworkProgram.part3;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 09:00
 **/
public class StudentSend {
    public static void main(String[] args) {
        new Thread(new UDPTalkThreadClient(7777, "localhost", 9999)).start();
        new Thread(new UDPTalkThreadServer(8888, "老师")).start();
    }
}
