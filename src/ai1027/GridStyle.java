
package ai1027;

import javax.swing.*;
import java.awt.*;

public class GridStyle {

    static Color[] colors = {
            new Color(233, 224, 215),
            new Color(222, 232, 225),
            new Color(233, 221, 218),
            new Color(231, 227, 214)
    };

    public static void applyPanel(JPanel panel) {
        panel.setBackground(new Color(248, 246, 242));
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 35, 35, 35)
        );
    }

    public static void applyTitle(JLabel title) {
        title.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        title.setForeground(new Color(77, 74, 67));
    }

    public static JButton createButton(String name, String desc, int i) {
        Color color = colors[i];

        String label = "<html><center><b>" + name
                + "</b><br><br>" + desc + "</center></html>";

        JButton button = new JButton(label) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(getModel().isRollover()
                        ? color.brighter() : color);

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        button.setForeground(new Color(77, 74, 67));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}

