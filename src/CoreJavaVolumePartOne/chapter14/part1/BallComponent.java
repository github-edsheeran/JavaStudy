package CoreJavaVolumePartOne.chapter14.part1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: CoreJavaVolumePartOne
 * @description: The component that draws the balls.
 * @chineseDescription: 程序清单14-3
 * @author: LiuDongMan
 * @createdDate: 2019-06-23 21:55
 **/
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;
    private List<Ball> balls = new ArrayList<>();

    /**
     * Add a ball to the component.
     * @param ball the ball to add
     */
    public void add(Ball ball) {
        balls.add(ball);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // erase background
        Graphics2D g2 = (Graphics2D) g;

        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
