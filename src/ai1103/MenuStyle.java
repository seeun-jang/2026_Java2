
package ai1103;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.*;

public class MenuStyle {

    // =========================
    // 1. 색상 설정
    // =========================
    public static final Color PASTEL_MINT =
            new Color(233, 249, 234);

    public static final Color PASTEL_PINK =
            new Color(255, 236, 242);

    public static final Color PASTEL_CREAM =
            new Color(255, 252, 244);

    private static final Color FRAME_BG =
            new Color(255, 250, 249);

    private static final Color GREEN =
            new Color(156, 206, 162);

    private static final Color DARK_GREEN =
            new Color(76, 123, 96);

    private static final Color PINK =
            new Color(245, 172, 195);

    private static final Color INK =
            new Color(96, 105, 95);

    private static final Color MUTED =
            new Color(158, 152, 148);

    private static final Color WHITE_PAPER =
            new Color(255, 253, 248);

    // =========================
    // 2. 메뉴바
    // =========================
    public static JMenuBar menuBar() {
        JMenuBar bar = new JMenuBar();

        bar.setBackground(Color.WHITE);
        bar.setBorder(new EmptyBorder(7, 14, 7, 14));

        return bar;
    }

    public static JMenu menu(String name) {
        JMenu menu = new JMenu(name);

        menu.setFont(new Font("Dialog", Font.BOLD, 13));
        menu.setForeground(DARK_GREEN);
        menu.setBorder(new EmptyBorder(5, 12, 5, 12));

        return menu;
    }

    public static JMenuItem item(String name) {
        JMenuItem item = new JMenuItem(name);

        item.setFont(new Font("Dialog", Font.PLAIN, 13));
        item.setForeground(INK);
        item.setBackground(Color.WHITE);
        item.setOpaque(true);

        item.setBorder(
                new EmptyBorder(9, 16, 9, 16)
        );

        return item;
    }

    // =========================
    // 3. 메인 화면
    // =========================
    public static JPanel mainPanel() {

        JPanel panel = new JPanel(
                new BorderLayout(0, 14)
        );

        panel.setBackground(FRAME_BG);
        panel.setBorder(
                new EmptyBorder(18, 25, 17, 25)
        );

        return panel;
    }

    // =========================
    // 4. 제목 영역
    // =========================
    public static JPanel headerPanel() {

        JPanel panel = new JPanel(
                new BorderLayout(0, 5)
        );

        panel.setOpaque(false);

        JLabel title = new JLabel(
                "Merry Little Notes",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Serif", Font.BOLD, 28)
        );
        title.setForeground(DARK_GREEN);

        JLabel subtitle = new JLabel(
                "♡  크리스마스 소원을 적어보세요  ♡",
                SwingConstants.CENTER
        );

        subtitle.setFont(
                new Font("Dialog", Font.PLAIN, 12)
        );

        subtitle.setForeground(
                new Color(180, 123, 144)
        );

        panel.add(title, BorderLayout.CENTER);
        panel.add(subtitle, BorderLayout.SOUTH);

        return panel;
    }

    // =========================
    // 5. 텍스트 영역
    // =========================
    public static JTextArea textArea() {

        JTextArea area = new JTextArea();

        area.setFont(
                new Font("Dialog", Font.PLAIN, 16)
        );

        area.setForeground(INK);
        area.setCaretColor(DARK_GREEN);
        area.setBackground(PASTEL_CREAM);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setMargin(
                new Insets(12, 12, 12, 12)
        );

        return area;
    }

    // =========================
    // 6. 상태 표시
    // =========================
    public static JLabel statusLabel(String message) {

        JLabel label = new JLabel(message);

        label.setFont(
                new Font("Dialog", Font.PLAIN, 12)
        );

        label.setForeground(MUTED);

        return label;
    }

    // =========================
    // 7. Clear 버튼
    // =========================
    public static JButton clearButton() {

        JButton button = new JButton("Clear note  ×");

        button.setFont(
                new Font("Dialog", Font.BOLD, 12)
        );

        button.setForeground(DARK_GREEN);
        button.setBackground(
                new Color(229, 244, 229)
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(9, 17, 9, 17)
        );

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================
    // 8. 하단 패널
    // =========================
    public static JPanel footerPanel(
            JLabel status,
            JButton clearButton
    ) {

        JPanel panel = new JPanel(
                new BorderLayout()
        );

        panel.setOpaque(false);

        panel.add(status, BorderLayout.WEST);
        panel.add(clearButton, BorderLayout.EAST);

        return panel;
    }

    // =========================
    // 9. 메모지 카드
    // =========================
    public static class NoteCard extends JPanel {

        private final JTextArea area;
        private final JScrollPane scrollPane;

        public NoteCard(JTextArea area) {

            super(new BorderLayout());

            this.area = area;

            setOpaque(false);

            setBorder(
                    new EmptyBorder(115, 91, 90, 91)
            );

            scrollPane = new JScrollPane(area);

            scrollPane.setBorder(
                    BorderFactory.createEmptyBorder()
            );

            scrollPane.setBackground(PASTEL_CREAM);

            scrollPane.getViewport().setBackground(
                    PASTEL_CREAM
            );

            scrollPane.getVerticalScrollBar()
                    .setUnitIncrement(16);

            add(scrollPane, BorderLayout.CENTER);
        }

        // 메모지 배경색 변경
        public void setPaperColor(Color color) {

            area.setBackground(color);
            scrollPane.setBackground(color);

            scrollPane.getViewport()
                    .setBackground(color);

            area.repaint();
        }

        // =========================
        // 10. 크리스마스 장식 그리기
        // =========================
        @Override
        protected void paintComponent(Graphics graphics) {

            super.paintComponent(graphics);

            Graphics2D g =
                    (Graphics2D) graphics.create();

            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = getWidth();
            int h = getHeight();

            // 민트색 프레임
            g.setColor(GREEN);
            g.fillRoundRect(
                    0, 0, w - 1, h - 1, 22, 22
            );

            g.setColor(new Color(131, 186, 143));
            g.setStroke(new BasicStroke(2));

            g.drawRoundRect(
                    1, 1, w - 3, h - 3, 22, 22
            );

            // 몽글몽글한 종이 테두리
            g.setColor(WHITE_PAPER);

            g.fillRoundRect(
                    68, 105,
                    w - 136, h - 186,
                    40, 40
            );

            for (int x = 90; x < w - 85; x += 35) {
                g.fillOval(x - 20, 86, 48, 48);
                g.fillOval(x - 20, h - 105, 48, 48);
            }

            for (int y = 130; y < h - 115; y += 34) {
                g.fillOval(47, y - 20, 48, 48);
                g.fillOval(w - 95, y - 20, 48, 48);
            }

            // 전구 줄 장식
            g.setStroke(new BasicStroke(2f));
            g.setColor(new Color(250, 253, 246));

            g.draw(
                    new QuadCurve2D.Double(
                            w * .25, 72,
                            w * .50, 101,
                            w * .75, 72
                    )
            );

            Color[] bulbs = {
                    new Color(249, 182, 184),
                    new Color(255, 229, 142),
                    new Color(248, 192, 208),
                    new Color(255, 237, 169),
                    new Color(249, 182, 184)
            };

            for (int i = 0; i < bulbs.length; i++) {

                int x = (int)(
                        w * (.30 + i * .10)
                );

                int y = 78 + (
                        i == 2 ? 18 :
                                i == 1 || i == 3 ? 11 : 0
                );

                g.setColor(bulbs[i]);
                g.fillOval(x - 9, y - 2, 18, 18);
            }

            // 분홍 리본
            drawBow(g, w / 2, 30);

            // 네 귀퉁이 토끼
            drawBunny(
                    g, 56, 60, .70,
                    new Color(255, 205, 206)
            );

            drawBunny(
                    g, w - 56, 66, .70,
                    new Color(255, 206, 208)
            );

            drawBunny(
                    g, 56, h - 78, .72,
                    new Color(214, 189, 156)
            );

            drawBunny(
                    g, w - 56, h - 76, .72,
                    new Color(252, 201, 213)
            );

            // 별 장식
            drawStar(
                    g, 108, 30, 10,
                    new Color(255, 239, 157)
            );

            drawStar(
                    g, w - 113, 35, 9,
                    new Color(255, 239, 157)
            );

            drawStar(
                    g, 31, h / 2, 9,
                    new Color(255, 244, 179)
            );

            drawStar(
                    g, w - 31, h / 2 + 25, 9,
                    new Color(255, 244, 179)
            );

            drawStar(
                    g, w / 2, h - 43, 9,
                    new Color(255, 244, 179)
            );

            // 작은 눈송이 장식
            g.setColor(new Color(243, 248, 245));

            int[][] points = {
                    {34, 114},
                    {w - 33, 150},
                    {35, h - 140},
                    {w - 36, h - 143},
                    {w / 2 - 100, h - 42},
                    {w / 2 + 100, h - 38}
            };

            for (int[] p : points) {
                g.fillOval(p[0], p[1], 6, 6);
            }

            g.dispose();
        }
    }

    // =========================
    // 11. 리본 그리기
    // =========================
    private static void drawBow(
            Graphics2D g, int x, int y
    ) {

        g.setColor(PINK);

        g.fillOval(x - 61, y - 20, 57, 43);
        g.fillOval(x + 4, y - 20, 57, 43);

        g.fill(
                new Polygon(
                        new int[]{x - 4, x - 29, x - 10},
                        new int[]{y + 6, y + 48, y + 42},
                        3
                )
        );

        g.fill(
                new Polygon(
                        new int[]{x + 4, x + 29, x + 10},
                        new int[]{y + 6, y + 48, y + 42},
                        3
                )
        );

        g.setColor(new Color(235, 154, 181));
        g.setStroke(new BasicStroke(1.5f));

        g.drawOval(x - 61, y - 20, 57, 43);
        g.drawOval(x + 4, y - 20, 57, 43);

        g.setColor(new Color(250, 196, 212));
        g.fillOval(x - 13, y - 8, 26, 27);

        drawStar(
                g, x, y + 5, 12,
                new Color(255, 235, 130)
        );
    }

    // =========================
    // 12. 토끼 그리기
    // =========================
    private static void drawBunny(
            Graphics2D graphics,
            int x, int y,
            double scale,
            Color sweater
    ) {

        Graphics2D g =
                (Graphics2D) graphics.create();

        g.translate(x, y);
        g.scale(scale, scale);

        // 귀
        g.setColor(new Color(253, 250, 245));

        g.fillOval(-31, -58, 23, 52);
        g.fillOval(7, -58, 23, 52);

        // 귀 안쪽
        g.setColor(new Color(252, 203, 216));

        g.fillOval(-25, -50, 11, 33);
        g.fillOval(13, -50, 11, 33);

        // 옷
        g.setColor(sweater);
        g.fillOval(-35, 14, 70, 44);

        // 얼굴
        g.setColor(new Color(255, 252, 247));
        g.fillOval(-39, -27, 78, 72);

        // 눈
        g.setColor(new Color(92, 86, 85));
        g.fillOval(-18, 3, 5, 6);
        g.fillOval(14, 3, 5, 6);

        // 볼터치
        g.setColor(new Color(250, 195, 204));

        g.fillOval(-29, 12, 16, 9);
        g.fillOval(14, 12, 16, 9);

        // 코
        g.setColor(new Color(211, 136, 145));
        g.fillOval(-3, 14, 6, 5);

        // 입
        g.setColor(new Color(130, 112, 104));
        g.setStroke(new BasicStroke(1.3f));

        g.drawArc(-5, 17, 6, 8, 180, 180);
        g.drawArc(1, 17, 6, 8, 180, 180);

        g.dispose();
    }

    // =========================
    // 13. 별 그리기
    // =========================
    private static void drawStar(
            Graphics2D g,
            int cx, int cy,
            int radius,
            Color color
    ) {

        Path2D path = new Path2D.Double();

        for (int i = 0; i < 10; i++) {

            double angle =
                    -Math.PI / 2 + i * Math.PI / 5;

            double r =
                    (i % 2 == 0) ?
                            radius : radius * .48;

            double px =
                    cx + Math.cos(angle) * r;

            double py =
                    cy + Math.sin(angle) * r;

            if (i == 0) {
                path.moveTo(px, py);
            } else {
                path.lineTo(px, py);
            }
        }

        path.closePath();

        g.setColor(color);
        g.fill(path);
    }
}
