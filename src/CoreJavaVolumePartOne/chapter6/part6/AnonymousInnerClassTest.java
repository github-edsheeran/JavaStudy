package CoreJavaVolumePartOne.chapter6.part6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates anonymous inner classes.
 * @chineseDescription: 程序清单6-8
 * @author: LiuDongMan
 * @createdDate: 2019-06-14 21:06
 **/
public class AnonymousInnerClassTest {
    public static void main(String[] args) {

    }
}

class TalkingClock {
    public void start(int interval, boolean beep) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };

        Timer timer = new Timer(interval, listener);
        timer.start();
    }
}
