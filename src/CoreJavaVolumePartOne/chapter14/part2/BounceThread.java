package CoreJavaVolumePartOne.chapter14.part2;

import CoreJavaVolumePartOne.chapter14.part1.Ball;
import CoreJavaVolumePartOne.chapter14.part1.BallComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @program: CoreJavaVolumePartOne
 * @description: Shows animated bouncing balls.
 * @chineseDescription: 程序清单14-4
 * @author: LiuDongMan
 * @createdDate: 2019-06-24 21:47
 **/
public class BounceThread {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setTitle("BounceThread");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame {
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BounceFrame() {
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", e -> addBall());
        addButton(buttonPanel, "End", e -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);
        Runnable r = () -> {
            try {
                for (int i = 1; i < STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.repaint();
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(r);
        t.start();
    }
}
