
package ai1103;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;

public class MenuStyle extends JPanel {

    public final NoteArea textArea = new NoteArea();
    public final JLabel status = new JLabel("Ready");

    private final JLabel title = new JLabel(
            "Merry Little Notes", SwingConstants.CENTER
    );

    private final JLabel subtitle = new JLabel(
            "♡ CHRISTMAS NOTE ♡", SwingConstants.CENTER
    );

    private final JToolBar toolbar = new JToolBar();
    private final NoteCard card = new NoteCard();

    private Color frame = new Color(159, 208, 165);
    private Color paper = new Color(255, 252, 245);
    private Color ribbon = new Color(244, 173, 196);
    private Color ink = new Color(80, 116, 87);

    public MenuStyle() {
        super(new BorderLayout(0, 14));

        setBorder(new EmptyBorder(17, 24, 15, 24));

        // 제목
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setOpaque(false);

        JPanel heading = new JPanel(new BorderLayout(0, 4));
        heading.setOpaque(false);

        title.setFont(new Font("Serif", Font.BOLD, 28));
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 12));

        heading.add(title, BorderLayout.NORTH);
        heading.add(subtitle, BorderLayout.SOUTH);
        top.add(heading, BorderLayout.NORTH);

        // 툴바
        toolbar.setFloatable(false);
        toolbar.setBackground(Color.WHITE);
        toolbar.setBorder(new EmptyBorder(5, 8, 5, 8));

        top.add(toolbar, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        // 메모장
        textArea.setFont(new Font("Dialog", Font.PLAIN, 16));
        textArea.setMargin(new Insets(12, 12, 12, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);

        card.setBorder(new EmptyBorder(122, 84, 90, 84));
        card.setLayout(new BorderLayout());

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        card.add(scroll, BorderLayout.CENTER);
        add(card, BorderLayout.CENTER);

        // 상태 라벨
        status.setFont(new Font("Dialog", Font.PLAIN, 12));
        add(status, BorderLayout.SOUTH);

        setTheme("Mint");
    }

    // =========================
    // 아이콘 툴바
    // =========================
    public void addToolbar(ActionListener fileListener,
                           ActionListener themeListener) {

        addButton("＋", "New", fileListener);
        addButton("▤", "Open", fileListener);
        addButton("▣", "Save", fileListener);
        addButton("×", "Clear", fileListener);

        toolbar.addSeparator(new Dimension(15, 25));

        addButton("●", "Mint", themeListener);
        addButton("●", "Pink", themeListener);
        addButton("●", "Snow", themeListener);
    }

    private void addButton(String icon, String command,
                           ActionListener listener) {

        JButton button = new JButton(icon);

        button.setActionCommand(command);
        button.setToolTipText(command);
        button.setFont(new Font("Dialog", Font.BOLD, 21));

        button.setForeground(
                command.equals("Mint") ? new Color(119, 183, 131) :
                        command.equals("Pink") ? new Color(238, 153, 183) :
                                command.equals("Snow") ? new Color(145, 189, 226) :
                                        ink
        );

        button.setBackground(new Color(250, 250, 248));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(48, 39));

        button.addActionListener(listener);
        toolbar.add(button);
    }

    // =========================
    // 크리스마스 테마 변경
    // =========================
    public void setTheme(String name) {

        Color background;

        switch (name) {

            case "Pink":
                background = new Color(255, 246, 249);
                frame = new Color(245, 184, 200);
                paper = new Color(255, 239, 245);
                ribbon = new Color(230, 148, 180);
                ink = new Color(145, 86, 112);

                subtitle.setText(
                        "♡ PINK RIBBON CHRISTMAS ♡"
                );
                break;

            case "Snow":
                background = new Color(245, 250, 255);
                frame = new Color(177, 211, 237);
                paper = new Color(251, 253, 255);
                ribbon = new Color(194, 174, 225);
                ink = new Color(83, 118, 152);

                subtitle.setText(
                        "❄ SNOWY CHRISTMAS ❄"
                );
                break;

            default:
                background = new Color(248, 252, 246);
                frame = new Color(159, 208, 165);
                paper = new Color(255, 252, 245);
                ribbon = new Color(244, 173, 196);
                ink = new Color(80, 116, 87);

                subtitle.setText(
                        "♡ MINT BUNNY CHRISTMAS ♡"
                );
        }

        setBackground(background);
        title.setForeground(ink);
        subtitle.setForeground(ink);
        status.setForeground(ink);
        status.setText("Theme: " + name);

        textArea.setForeground(ink);
        textArea.setCaretColor(ink);
        textArea.setBackground(paper);

        textArea.setLineColor(
                new Color(
                        ink.getRed(),
                        ink.getGreen(),
                        ink.getBlue(),
                        55
                )
        );

        JScrollPane scroll =
                (JScrollPane) textArea.getParent().getParent();

        scroll.getViewport().setBackground(paper);

        card.repaint();
        textArea.repaint();
    }

    // =========================
    // 공책 가로줄
    // =========================
    public static class NoteArea extends JTextArea {

        private Color lineColor =
                new Color(140, 170, 150, 65);

        public void setLineColor(Color color) {
            lineColor = color;
        }

        @Override
        protected void paintComponent(Graphics g) {

            g.setColor(lineColor);

            int height =
                    getFontMetrics(getFont()).getHeight();

            int start = getInsets().top + height - 2;

            for (int y = start; y < getHeight(); y += height) {
                g.drawLine(8, y, getWidth() - 8, y);
            }

            super.paintComponent(g);
        }
    }

    // =========================
    // 크리스마스 메모지
    // =========================
    private class NoteCard extends JPanel {

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

            // 파스텔 프레임
            g.setColor(frame);
            g.fillRoundRect(0, 0, w, h, 20, 20);

            // 둥근 메모지
            g.setColor(paper);
            g.fillRoundRect(
                    62, 104, w - 124, h - 180, 36, 36
            );

            // 위아래 종이 테두리
            for (int x = 85; x < w - 75; x += 34) {
                g.fillOval(x - 18, 87, 45, 45);
                g.fillOval(x - 18, h - 106, 45, 45);
            }

            // 좌우 종이 테두리
            for (int y = 130; y < h - 104; y += 34) {
                g.fillOval(44, y - 20, 47, 47);
                g.fillOval(w - 91, y - 20, 47, 47);
            }

            // 분홍 리본
            g.setColor(ribbon);

            g.fillOval(w / 2 - 55, 12, 54, 39);
            g.fillOval(w / 2 + 1, 12, 54, 39);
            g.fillOval(w / 2 - 11, 24, 22, 23);

            g.fillPolygon(
                    new int[]{w/2-5, w/2-27, w/2-7},
                    new int[]{43, 70, 63}, 3
            );

            g.fillPolygon(
                    new int[]{w/2+5, w/2+27, w/2+7},
                    new int[]{43, 70, 63}, 3
            );

            g.setColor(new Color(255, 235, 142));
            g.fillOval(w/2-6, 28, 12, 12);

            // 크리스마스 전구
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2f));

            g.draw(
                    new QuadCurve2D.Double(
                            w * .25, 80,
                            w * .5, 117,
                            w * .75, 80
                    )
            );

            Color[] lights = {
                    new Color(249, 184, 193),
                    new Color(255, 233, 149),
                    new Color(244, 186, 202),
                    new Color(255, 230, 145),
                    new Color(249, 184, 193)
            };

            for (int i = 0; i < lights.length; i++) {

                g.setColor(lights[i]);

                g.fillOval(
                        (int)(w * (.30 + i * .10)) - 8,
                        85 + (2 - Math.abs(2 - i)) * 7,
                        16, 16
                );
            }

            // 토끼 4마리
            bunny(
                    g, 51, 64,
                    new Color(252, 190, 199)
            );

            bunny(
                    g, w-51, 64,
                    new Color(245, 187, 193)
            );

            bunny(
                    g, 51, h-68,
                    new Color(212, 182, 157)
            );

            bunny(
                    g, w-51, h-68,
                    new Color(249, 187, 208)
            );

            // 별 장식
            g.setColor(new Color(255, 239, 170));

            for (int[] p : new int[][]{
                    {104, 30},
                    {w-104, 31},
                    {29, h/2},
                    {w-30, h/2},
                    {w/2, h-35}
            }) {
                g.fillOval(
                        p[0]-5, p[1]-5, 10, 10
                );
            }

            // 작은 눈 장식
            g.setColor(Color.WHITE);

            for (int[] p : new int[][]{
                    {29, 115},
                    {w-28, 155},
                    {31, h-145},
                    {w-31, h-148}
            }) {
                g.fillOval(p[0], p[1], 6, 6);
            }

            g.dispose();
        }

        // 토끼 캐릭터
        private void bunny(
                Graphics2D g, int x, int y, Color coat
        ) {

            Graphics2D b = (Graphics2D) g.create();

            b.translate(x, y);
            b.scale(.75, .75);

            // 귀
            b.setColor(new Color(255, 253, 249));
            b.fillOval(-29, -57, 23, 50);
            b.fillOval(6, -57, 23, 50);

            // 귀 안쪽
            b.setColor(new Color(250, 205, 215));
            b.fillOval(-23, -48, 10, 31);
            b.fillOval(12, -48, 10, 31);

            // 옷
            b.setColor(coat);
            b.fillOval(-33, 15, 66, 40);

            // 얼굴
            b.setColor(new Color(255, 253, 249));
            b.fillOval(-38, -27, 76, 69);

            // 눈
            b.setColor(new Color(93, 90, 89));
            b.fillOval(-18, 4, 5, 5);
            b.fillOval(13, 4, 5, 5);

            // 볼
            b.setColor(new Color(251, 192, 201));
            b.fillOval(-28, 13, 14, 9);
            b.fillOval(14, 13, 14, 9);

            // 코
            b.setColor(new Color(204, 135, 146));
            b.fillOval(-3, 16, 6, 5);

            b.dispose();
        }
    }
}
