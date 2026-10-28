package ai1028;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.drawLine(40, 25, 185, 25);

        g.setColor(Color.YELLOW);
        g.fillRect(30, 45, 135, 200);

        g.setColor(Color.RED);
        g.drawOval(30, 45, 135, 200);

        g.setColor(Color.MAGENTA);
        g.fillOval(190, 45, 150, 150);

        g.setColor(new Color(220, 210, 120));
        g.drawRect(190, 45, 150, 150);


        int centerX = 265;
        int centerY = 120;
        int outerR = 55;
        int innerR = 23;

        int[] x = new int[10];
        int[] y = new int[10];

        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(-90 + i * 36);
            int r = (i % 2 == 0) ? outerR : innerR;

            x[i] = centerX + (int)(Math.cos(angle) * r);
            y[i] = centerY + (int)(Math.sin(angle) * r);
        }

        g.setColor(Color.YELLOW);
        g.fillPolygon(x, y, 10);

        g.setColor(Color.BLUE);
        g.drawPolygon(x, y, 10);
    }
}