
package ai1027;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

// JFrame의 5개의 영역(위치)에 버튼을 추가하시오.
// 1.버튼 객체 생성
// 2.JFrame의 각 위치에 추가
// 각 버튼을 클릭하면 버튼의 라벨 문자열이 선택되었다는 메세지창을 보이시오. (Event Programming)

public class BorderTest extends JFrame {

    boolean dnd = false;

    public BorderTest() {
        super("고요 | GOYO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // BorderLayout 수평, 수직 간격 10px
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        BorderStyle.applyPanel(panel);
        setContentPane(panel);

        // 상단 타이틀
        JLabel title = BorderStyle.createTitle("고요 | GOYO");
        panel.add(title, BorderLayout.NORTH);

        // 버튼 2개
        ArrayList<String> names = new ArrayList<>(Arrays.asList(
                "수면모드 ON", "수면모드 OFF"
        ));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 0));
        buttons.setOpaque(false);

        for (int i = 0; i < names.size(); i++) {
            JButton button = BorderStyle.createButton(names.get(i), i);
            buttons.add(button);

            final int index = i;

            button.addActionListener(e -> {
                dnd = (index == 0);

                JOptionPane.showMessageDialog(this,
                        button.getText() + "이(가) 선택되었습니다.");
            });
        }

        panel.add(buttons, BorderLayout.CENTER);

        setSize(560, 280);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BorderTest();
    }
}
