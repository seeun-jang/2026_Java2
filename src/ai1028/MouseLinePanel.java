
package ai1028;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseLinePanel extends JPanel {

    int x1, y1, x2, y2;
    boolean drawn = false;

    public MouseLinePanel() {
        setBackground(new Color(255, 245, 168));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = x2 = e.getX();
                y1 = y2 = e.getY();
                drawn = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                repaint();
            }
        });

        // 드래그하는 동안 선 그리기
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!drawn) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // 선 그리기
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(x1, y1, x2, y2);

        // 시작점 (빨간 점)
        g2.setColor(Color.RED);
        g2.fillOval(x1 - 5, y1 - 5, 10, 10);

        // 끝점 (파란 점)
        g2.setColor(Color.BLUE);
        g2.fillOval(x2 - 5, y2 - 5, 10, 10);

        // 좌표값 표시
        g2.setColor(Color.BLACK);
        g2.drawString("시작 (" + x1 + ", " + y1 + ")",
                x1 + 10, y1 + 20);

        g2.drawString("끝 (" + x2 + ", " + y2 + ")",
                x2 + 10, y2 - 10);

        // 상단 좌표 정보
        g2.drawString(
                "시작: (" + x1 + ", " + y1 + ")   끝: ("
                        + x2 + ", " + y2 + ")", 15, 25);
    }
}
