package SXT._7ManualServer.part2;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-09-05 09:12
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 7777);
        OutputStream os = client.getOutputStream();
        String msg = "woca";
        os.write(msg.getBytes(), 0, msg.getBytes().length);
    }
}
