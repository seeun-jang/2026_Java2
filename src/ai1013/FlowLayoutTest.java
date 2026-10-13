
package ai1013;

import center.CenterFramePoint;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class FlowLayoutTest extends JFrame {

    // 디자인 색상
    private final Color NAVY = new Color(30, 44, 70);
    private final Color BG = new Color(245, 247, 251);

    // 버튼별 배경색
    private final Color[] colors = {
            new Color(218, 236, 255), // 스카이
            new Color(218, 245, 230), // 민트
            new Color(237, 226, 255), // 라벤더
            new Color(255, 230, 220), // 피치
            new Color(255, 245, 212)  // 크림
    };

    private final String[] names = {
            "SKY", "MINT", "LAVENDER", "PEACH", "CREAM"
    };

    private final JButton[] buttons = new JButton[5];

    public FlowLayoutTest() {

        setTitle("Color Palette - FlowLayout 연습");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 전체 화면
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        setContentPane(root);

        // =========================
        // 1. 상단 헤더
        // =========================
        JPanel header = new JPanel();
        header.setBackground(NAVY);
        header.setLayout(new BoxLayout(
                header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(
                25, 30, 25, 30));

        JLabel title = new JLabel("COLOR PALETTE");
        title.setFont(new Font(
                "맑은 고딕", Font.BOLD, 25));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "FlowLayout  |  원하는 색상을 선택하세요");
        subtitle.setFont(new Font(
                "맑은 고딕", Font.PLAIN, 13));
        subtitle.setForeground(new Color(190, 207, 230));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);

        root.add(header, BorderLayout.NORTH);

        // =========================
        // 2. 배경색 변경 영역
        // =========================
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG);

        // FlowLayout 적용
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setOpaque(false);

        // 중앙 안내 문구
        JLabel message = new JLabel("CHOOSE A COLOR");
        message.setFont(new Font(
                "맑은 고딕", Font.BOLD, 29));
        message.setForeground(NAVY);
        message.setHorizontalAlignment(JLabel.CENTER);

        JLabel colorInfo = new JLabel(
                "버튼을 클릭하면 배경색이 변경됩니다.");
        colorInfo.setFont(new Font(
                "맑은 고딕", Font.PLAIN, 14));
        colorInfo.setForeground(new Color(90, 105, 125));
        colorInfo.setHorizontalAlignment(JLabel.CENTER);

        JPanel textPanel = new JPanel(new GridLayout(
                2, 1, 0, 12));
        textPanel.setOpaque(false);
        textPanel.add(message);
        textPanel.add(colorInfo);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(textPanel);

        // =========================
        // 3. 버튼 5개 생성
        // =========================
        for (int i = 0; i < 5; i++) {

            final int index = i;

            JButton btn = new JButton(names[i]) {

                private boolean selected = false;

                @Override
                protected void paintComponent(Graphics g) {

                    Graphics2D g2 = (Graphics2D) g.create();

                    g2.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    if (selected) {
                        g2.setColor(NAVY);
                    } else if (getModel().isRollover()) {
                        g2.setColor(new Color(220, 230, 245));
                    } else {
                        g2.setColor(Color.WHITE);
                    }

                    g2.fill(new RoundRectangle2D.Double(
                            0, 0, getWidth(), getHeight(),
                            22, 22));

                    g2.dispose();
                    super.paintComponent(g);
                }

                public void setSelected(boolean value) {
                    selected = value;
                    setForeground(value ? Color.WHITE : NAVY);
                    repaint();
                }
            };

            btn.setFont(new Font(
                    "맑은 고딕", Font.BOLD, 12));
            btn.setPreferredSize(new Dimension(105, 45));
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setRolloverEnabled(true);

            buttons[i] = btn;

            // =========================
            // 4. 버튼 클릭 이벤트
            // =========================
            btn.addActionListener(e -> {

                // 배경색 변경
                mainPanel.setBackground(colors[index]);

                // 선택한 색상 이름 출력
                message.setText(names[index]);

                // RGB 값 표시
                Color c = colors[index];

                String hex = String.format(
                        "#%02X%02X%02X",
                        c.getRed(),
                        c.getGreen(),
                        c.getBlue());

                colorInfo.setText("COLOR CODE  " + hex);

                // 선택한 버튼 강조
                for (int j = 0; j < buttons.length; j++) {

                    buttons[j].setForeground(
                            j == index ? Color.WHITE : NAVY);

                    buttons[j].setBackground(
                            j == index ? NAVY : Color.WHITE);
                }

                // 선택 상태 변경
                btn.setForeground(Color.WHITE);
                btn.repaint();

                // 모든 버튼 다시 그리기
                for (JButton button : buttons) {
                    button.repaint();
                }
            });

            buttonPanel.add(btn);
        }

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // =========================
        // 5. 하단 안내 문구
        // =========================
        JLabel footer = new JLabel(
                "JAVA SWING  /  FLOW LAYOUT",
                JLabel.CENTER);
        footer.setFont(new Font(
                "맑은 고딕", Font.PLAIN, 11));
        footer.setForeground(new Color(130, 145, 165));
        footer.setBorder(BorderFactory.createEmptyBorder(
                15, 0, 20, 0));

        footer.setOpaque(false);
        mainPanel.add(footer, BorderLayout.SOUTH);

        root.add(mainPanel, BorderLayout.CENTER);

        // =========================
        // 6. 창 중앙 배치
        // =========================
        int w = 650, h = 430;

        Point point = CenterFramePoint.getLocation(w, h);
        setBounds(point.x, point.y, w, h);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlowLayoutTest::new);
    }
}
