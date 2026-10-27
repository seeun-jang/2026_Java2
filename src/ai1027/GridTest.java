
package ai1027;

import center.SetFrame;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GridTest extends JFrame {

    Random random = new Random();
    int count = 0;

    Color[] colors = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.MAGENTA, Color.ORANGE
    };

    public GridTest() {
        makeGrid(null);
        SetFrame.setFrame(this, "주사위 숫자 채우기", 500, 500);
    }

    void makeGrid(Color selectedColor) {

        // 행과 열을 1~10 사이에서 무작위 생성
        int rows, cols;

        do {
            rows = random.nextInt(10) + 1;
            cols = random.nextInt(10) + 1;
        } while (rows * cols == count);

        count = rows * cols;

        // 기존 버튼 제거 후 새 격자 생성
        getContentPane().removeAll();
        setLayout(new GridLayout(rows, cols, 2, 2));

        for (int i = 0; i < count; i++) {
            int dice = random.nextInt(6) + 1;

            JButton button = new JButton("" + dice);

            // 처음에는 6가지 색상, 클릭 후에는 단일 색상
            button.setBackground(selectedColor == null
                    ? colors[dice - 1] : selectedColor);

            button.setOpaque(true);
            button.setFocusPainted(false);
            button.setMargin(new Insets(0, 0, 0, 0));

            // 클릭하면 색상 통일 + 버튼 개수 변경
            button.addActionListener(e ->
                    makeGrid(button.getBackground())
            );

            add(button);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new GridTest();
    }
}
