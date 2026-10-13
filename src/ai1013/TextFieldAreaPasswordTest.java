
package ai1013;

import center.CenterFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class TextFieldAreaPasswordTest extends JFrame {

    // 디자인 색상
    private final Color NAVY = new Color(29, 44, 70);
    private final Color BLUE = new Color(65, 105, 175);
    private final Color BG = new Color(243, 246, 251);
    private final Color FIELD_BG = new Color(248, 250, 253);
    private final Color BORDER = new Color(220, 227, 237);

    public TextFieldAreaPasswordTest() {

        setTitle("문자열 입력 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 전체 배경
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        setContentPane(root);

        // ============================
        // 1. 상단 헤더
        // ============================
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(NAVY);
        header.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("USER INFORMATION");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("사용자 정보를 입력해주세요.");
        subtitle.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        subtitle.setForeground(new Color(185, 199, 220));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);

        root.add(header, BorderLayout.NORTH);

        // ============================
        // 2. 중앙 입력 카드
        // ============================
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG);
        wrapper.setBorder(new EmptyBorder(20, 22, 20, 22));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(25, 26, 25, 26));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        // 아이디
        JTextField idField = new JTextField();
        styleField(idField);

        g.gridy = 0;
        g.insets = new Insets(0, 0, 8, 0);
        form.add(createLabel("아이디"), g);

        g.gridy = 1;
        g.insets = new Insets(0, 0, 20, 0);
        form.add(idField, g);

        // 비밀번호
        JPasswordField pwField = new JPasswordField();
        styleField(pwField);

        g.gridy = 2;
        g.insets = new Insets(0, 0, 8, 0);
        form.add(createLabel("비밀번호"), g);

        g.gridy = 3;
        g.insets = new Insets(0, 0, 20, 0);
        form.add(pwField, g);

        // ============================
        // 3. 내용 입력창
        // ============================
        JTextArea area = new JTextArea(4, 20);
        area.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        area.setForeground(NAVY);
        area.setBackground(FIELD_BG);
        area.setCaretColor(BLUE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(
                BorderFactory.createLineBorder(BORDER, 1, true)
        );
        scroll.setPreferredSize(new Dimension(330, 115));

        // 입력창 선택 효과
        area.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                scroll.setBorder(
                        BorderFactory.createLineBorder(BLUE, 1, true)
                );
            }

            public void focusLost(FocusEvent e) {
                scroll.setBorder(
                        BorderFactory.createLineBorder(BORDER, 1, true)
                );
            }
        });

        g.gridy = 4;
        g.insets = new Insets(0, 0, 8, 0);
        form.add(createLabel("내용 입력"), g);

        g.gridy = 5;
        g.insets = new Insets(0, 0, 8, 0);
        form.add(scroll, g);

        // 안내 문구
        JLabel hint = new JLabel("자유롭게 내용을 작성해주세요.");
        hint.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        hint.setForeground(new Color(145, 155, 170));

        g.gridy = 6;
        g.insets = new Insets(0, 0, 22, 0);
        form.add(hint, g);

        // ============================
        // 4. 입력 확인 버튼
        // ============================
        JButton btn = new JButton("입력 확인  →");
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(NAVY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(330, 47));

        // 버튼 마우스 효과
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BLUE);
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(NAVY);
            }
        });

        // 버튼 클릭 이벤트
        btn.addActionListener(e -> {
            String id = idField.getText().trim();
            String content = area.getText().trim();
            int pwLength = pwField.getPassword().length;

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "아이디를 입력해주세요.");
                idField.requestFocusInWindow();
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "아이디: " + id +
                            "\n내용: " + content +
                            "\n비밀번호: " + pwLength + "자리",
                    "입력 정보 확인",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        g.gridy = 7;
        g.insets = new Insets(0, 0, 0, 0);
        form.add(btn, g);

        wrapper.add(form, BorderLayout.CENTER);
        root.add(wrapper, BorderLayout.CENTER);

        // ============================
        // 5. 창 크기 및 중앙 배치
        // ============================
        int w = 470, h = 600;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);

        setVisible(true);
    }

    // 라벨 디자인
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        label.setForeground(NAVY);
        return label;
    }

    // 입력창 디자인
    private void styleField(JTextField field) {

        field.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        field.setBackground(FIELD_BG);
        field.setForeground(NAVY);
        field.setCaretColor(BLUE);
        field.setPreferredSize(new Dimension(330, 43));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));

        // 클릭 시 테두리 색상 변경
        field.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BLUE, 1, true),
                        new EmptyBorder(8, 12, 8, 12)
                ));
            }

            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER, 1, true),
                        new EmptyBorder(8, 12, 8, 12)
                ));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                TextFieldAreaPasswordTest::new
        );
    }
}
