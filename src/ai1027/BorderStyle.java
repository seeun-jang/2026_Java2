
package ai1027;

import javax.swing.*;
import java.awt.*;

public class BorderStyle {

    public static void applyPanel(JPanel panel) {
        panel.setBackground(new Color(247, 248, 244));
        panel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 30, 30)
        );
    }

    public static JLabel createTitle(String text) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        title.setForeground(new Color(72, 85, 75));
        title.setPreferredSize(new Dimension(0, 65));
        return title;
    }

    public static JButton createButton(String text, int i) {

        Color color = (i == 0)
                ? new Color(142, 171, 152)
                : new Color(235, 207, 198);

        JButton button = new JButton(text) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(getModel().isRollover()
                        ? color.darker() : color);

                g2.fillRoundRect(0, 0, getWidth(),
                        getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setForeground(i == 0
                ? Color.WHITE : new Color(105, 83, 77));

        button.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}

