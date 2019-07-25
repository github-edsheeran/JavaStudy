package CoreJavaVolumePartOne.chapter6.part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单6-3
 * @author: LiuDongMan
 * @createdDate: 2019-05-25 15:57
 **/
public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(10000, listener);
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}