
package ai1013;

import center.CenterFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class ComboBoxImageTest extends JFrame {

    // 디자인 색상
    private final Color NAVY = new Color(29, 44, 70);
    private final Color BLUE = new Color(65, 105, 175);
    private final Color BG = new Color(243, 246, 251);
    private final Color LIGHT = new Color(248, 250, 253);

    // 동물 이름
    private final String[] animals = {
            "강아지", "고양이", "새", "판다"
    };

    // 아이콘 파일
    private final String[] iconPaths = {
            "imgs/dogIcon.png",
            "imgs/free-icon-cat-3677155.png",
            "imgs/free-icon-twitter-bird-logo-81725.png",
            "imgs/free-icon-panda-2675612.png"
    };

    // 실제 표시할 사진 파일
    private final String[] imagePaths = {
            "imgs/dog.png",
            "imgs/cat.jpg",
            "imgs/bird.jpg",
            "imgs/panda.jpg"
    };

    private JLabel imageLabel;
    private JLabel animalName;
    private JComboBox<String> comboBox;
    private AnimalButton[] buttons = new AnimalButton[4];

    public ComboBoxImageTest() {

        setTitle("Animal Gallery - 동물 갤러리");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ============================
        // 1. 전체 배경
        // ============================
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        setContentPane(root);

        // ============================
        // 2. 상단 헤더
        // ============================
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(
                        0, 0, NAVY,
                        getWidth(), getHeight(), BLUE
                ));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };

        header.setPreferredSize(new Dimension(0, 105));
        header.setBorder(new EmptyBorder(22, 30, 20, 30));

        JPanel headerText = new JPanel();
        headerText.setOpaque(false);
        headerText.setLayout(new BoxLayout(
                headerText, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ANIMAL GALLERY");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("아이콘을 클릭하여 동물을 만나보세요.");
        subtitle.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        subtitle.setForeground(new Color(215, 226, 245));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerText.add(title);
        headerText.add(Box.createVerticalStrut(7));
        headerText.add(subtitle);

        header.add(headerText, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        // ============================
        // 3. 메인 카드
        // ============================
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(20, 22, 22, 22));

        RoundedPanel card = new RoundedPanel(Color.WHITE, 24);
        card.setLayout(new BorderLayout(0, 20));
        card.setBorder(new EmptyBorder(24, 24, 24, 24));

        JPanel controls = new JPanel();
        controls.setOpaque(false);
        controls.setLayout(new BoxLayout(
                controls, BoxLayout.Y_AXIS));

        // ============================
        // 4. 콤보박스
        // ============================
        JLabel selectTitle = createLabel("동물 선택");

        comboBox = new JComboBox<>(animals);
        comboBox.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(NAVY);
        comboBox.setFocusable(false);
        comboBox.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44));
        comboBox.setPreferredSize(new Dimension(350, 44));
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(214, 224, 238), 1, true),
                new EmptyBorder(2, 10, 2, 4)
        ));

        // 콤보박스 화살표 디자인
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton arrow = new JButton("▼");
                arrow.setFont(new Font(
                        "맑은 고딕", Font.PLAIN, 11));
                arrow.setForeground(BLUE);
                arrow.setContentAreaFilled(false);
                arrow.setBorderPainted(false);
                arrow.setFocusPainted(false);
                arrow.setCursor(
                        new Cursor(Cursor.HAND_CURSOR));

                return arrow;
            }
        });

        // 드롭다운 목록 디자인
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value,
                    int index, boolean isSelected,
                    boolean cellHasFocus) {

                JLabel label = (JLabel) super
                        .getListCellRendererComponent(
                                list, value, index,
                                isSelected, cellHasFocus);

                label.setFont(new Font(
                        "맑은 고딕", Font.PLAIN, 14));

                label.setBorder(new EmptyBorder(
                        9, 12, 9, 12));

                if (isSelected && index >= 0) {
                    label.setBackground(
                            new Color(228, 237, 251));
                    label.setForeground(NAVY);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(NAVY);
                }

                return label;
            }
        });

        controls.add(selectTitle);
        controls.add(Box.createVerticalStrut(9));
        controls.add(comboBox);
        controls.add(Box.createVerticalStrut(22));

        // ============================
        // 5. 동물 아이콘 버튼 4개
        // ============================
        JLabel iconTitle = createLabel("빠른 선택");
        controls.add(iconTitle);
        controls.add(Box.createVerticalStrut(12));

        JPanel iconPanel = new JPanel(
                new GridLayout(1, 4, 10, 0));
        iconPanel.setOpaque(false);
        iconPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 105));
        iconPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (int i = 0; i < animals.length; i++) {

            final int index = i;

            buttons[i] = new AnimalButton(
                    animals[i], iconPaths[i]);

            buttons[i].addActionListener(e -> {
                comboBox.setSelectedIndex(index);
                selectAnimal(index);
            });

            iconPanel.add(buttons[i]);
        }

        controls.add(iconPanel);
        card.add(controls, BorderLayout.NORTH);

        // ============================
        // 6. 이미지 표시 영역
        // ============================
        RoundedPanel imagePanel = new RoundedPanel(LIGHT, 20);
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(
                new EmptyBorder(15, 15, 15, 15));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setFont(new Font(
                "맑은 고딕", Font.PLAIN, 13));
        imageLabel.setForeground(BLUE);

        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // 하단 동물 이름
        animalName = new JLabel("강아지");
        animalName.setFont(new Font(
                "맑은 고딕", Font.BOLD, 20));
        animalName.setForeground(NAVY);
        animalName.setHorizontalAlignment(JLabel.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(
                new EmptyBorder(12, 0, 0, 0));
        bottomPanel.add(animalName, BorderLayout.CENTER);

        imagePanel.add(bottomPanel, BorderLayout.SOUTH);
        card.add(imagePanel, BorderLayout.CENTER);

        wrapper.add(card, BorderLayout.CENTER);
        root.add(wrapper, BorderLayout.CENTER);

        // ============================
        // 7. 콤보박스 선택 이벤트
        // ============================
        comboBox.addActionListener(e -> {
            int index = comboBox.getSelectedIndex();

            if (index >= 0) {
                selectAnimal(index);
            }
        });

        // 처음 실행 시 강아지 선택
        selectAnimal(0);

        // ============================
        // 8. 창 크기 및 중앙 배치
        // ============================
        int w = 510, h = 660;
        int[] locations = CenterFrame.getLocation(w, h);

        setBounds(locations[0], locations[1], w, h);
        setMinimumSize(new Dimension(480, 620));
        setVisible(true);
    }

    // ============================
    // 동물 선택 함수
    // ============================
    private void selectAnimal(int index) {

        animalName.setText(animals[index]);

        // 실제 동물 사진 변경
        showImage(imagePaths[index]);

        // 선택한 아이콘 강조
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSelectedState(i == index);
        }
    }

    // ============================
    // 동물 사진 출력 함수
    // ============================
    private void showImage(String path) {

        File file = new File(path);

        if (!file.exists()) {
            imageLabel.setIcon(null);
            imageLabel.setText("사진 파일이 없습니다: " + path);
            return;
        }

        ImageIcon icon = new ImageIcon(path);

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        if (width <= 0 || height <= 0) {
            imageLabel.setIcon(null);
            imageLabel.setText("이미지를 불러올 수 없습니다.");
            return;
        }

        // 원본 비율 유지
        double scale = Math.min(
                350.0 / width, 220.0 / height);

        scale = Math.min(scale, 1.0);

        int newWidth = Math.max(
                1, (int) (width * scale));
        int newHeight = Math.max(
                1, (int) (height * scale));

        Image img = icon.getImage().getScaledInstance(
                newWidth, newHeight, Image.SCALE_SMOOTH);

        imageLabel.setText("");
        imageLabel.setIcon(new ImageIcon(img));
    }

    // ============================
    // 라벨 생성 함수
    // ============================
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setFont(new Font(
                "맑은 고딕", Font.BOLD, 14));
        label.setForeground(NAVY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        return label;
    }

    // ============================
    // 동물 아이콘 버튼 디자인
    // ============================
    private class AnimalButton extends JButton {

        private boolean selectedState = false;
        private boolean hovered = false;

        public AnimalButton(String name, String iconPath) {

            super(name);

            setFont(new Font(
                    "맑은 고딕", Font.BOLD, 12));
            setForeground(NAVY);

            setHorizontalAlignment(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.CENTER);
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setIconTextGap(8);

            setBorder(new EmptyBorder(8, 4, 8, 4));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // 아이콘 크기 조절
            ImageIcon icon = new ImageIcon(iconPath);

            if (icon.getIconWidth() > 0 &&
                    icon.getIconHeight() > 0) {

                Image img = icon.getImage().getScaledInstance(
                        52, 52, Image.SCALE_SMOOTH);

                setIcon(new ImageIcon(img));
            }

            // 마우스 호버 효과
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }
            });
        }

        public void setSelectedState(boolean selected) {
            selectedState = selected;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // 버튼 배경
            if (selectedState) {
                g2.setColor(new Color(228, 237, 251));
            } else if (hovered) {
                g2.setColor(new Color(243, 247, 253));
            } else {
                g2.setColor(Color.WHITE);
            }

            g2.fillRoundRect(
                    1, 1,
                    getWidth() - 3, getHeight() - 3,
                    18, 18);

            // 버튼 테두리
            if (selectedState) {
                g2.setColor(BLUE);
                g2.setStroke(new BasicStroke(2));
            } else {
                g2.setColor(new Color(220, 227, 237));
                g2.setStroke(new BasicStroke(1));
            }

            g2.drawRoundRect(
                    1, 1,
                    getWidth() - 3, getHeight() - 3,
                    18, 18);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ============================
    // 둥근 카드 패널
    // ============================
    private class RoundedPanel extends JPanel {

        private final Color color;
        private final int radius;

        public RoundedPanel(Color color, int radius) {
            this.color = color;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Double(
                    0, 0, getWidth(), getHeight(),
                    radius, radius));

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComboBoxImageTest::new);
    }
}
