package CoreJavaVolumePartOne.chapter6.part5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates the use of inner classes.
 * @chineseDescription: 程序清单6-7
 * @author: LiuDongMan
 * @createdDate: 2019-06-07 09:40
 **/
public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(/*5000, true*/);
        clock.start(5000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {
//    private int interval;
//    private boolean beep;
//
//    public TalkingClock(int interval, boolean beep) {
//        this.interval = interval;
//        this.beep = beep;
//    }

    /**
     * 局部内部类可以访问局部变量，前提是变量必须是final的，而且在JDK8以前会强制要求加上final修饰符，因为局部内部类所生成
     * 的对象如果还有地方引用它的话，其生命周期必然比实例方法长，加上final之后会在内部类对象中对局部变量进行拷贝，从而保证
     * 其生命周期。因此TalkingClock类不再需要存储实例变量interval和beep了
     * @param interval
     * @param beep
     */
    public void start(int interval, boolean beep) {
        /**
         * Part2
         */
//        class TimePrinter implements ActionListener {   // 局部内部类不能用public或private访问说明符进行声明，因为局部变量也是如此
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("At the tone, the time is " + new Date());
//                if (beep/*TalkingClock.this.beep*/) {
//                    System.out.println("进来了");
//                    Toolkit.getDefaultToolkit().beep();
//                }
//            }
//        }

        // Part3
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };

        Timer t = new Timer(interval, listener);
        t.start();
    }

    /**
     * Part1
     */
//    public class TimePrinter implements ActionListener {
////        public TimePrinter() {
////            System.out.println("Generate a TimePrinter object.");   // 并不是每一个TalkingClock都有一个TimePrinter实例，只有在调用start方法的情况下才会生成对应的内部类实例
////        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("At the tone, the time is " + new Date());
//            if (beep/*TalkingClock.this.beep*/) {
//                Toolkit.getDefaultToolkit().beep();
//            }
//        }
//    }
}
