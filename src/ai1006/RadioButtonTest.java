package ai1006;

import center.CenterFrame;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonTest extends JFrame {

    ImageIcon[] imageIcons;
    JLabel lbl;
    JRadioButton[] radios;

    public RadioButtonTest() {
        setTitle("라디오버튼 테스트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panNorth = new JPanel();   // 작은 박스

        String[] entertainers = {
                "코르티스",
                "라이즈",
                "NCTwish"
        };

        radios = new JRadioButton[entertainers.length];
        imageIcons = new ImageIcon[entertainers.length];
        lbl = new JLabel();

        ButtonGroup group = new ButtonGroup();

        int i = 0;
        for (String entertainer : entertainers) {

            // 실제 파일명에 맞춤
            if (i == 0) {
                imageIcons[i] = new ImageIcon("imgs/Cortis1.jpg");
            } else if (i == 1) {
                imageIcons[i] = new ImageIcon("imgs/riize1.jpg");
            } else {
                imageIcons[i] = new ImageIcon("imgs/NCTwish.jpg");
            }

            radios[i] = new JRadioButton(entertainer);
            group.add(radios[i]);
            radios[i].addItemListener(radioListener);
            panNorth.add(radios[i]);

            i++;
        }

        // 처음 실행 시 첫 번째 사진 보이기
        radios[0].setSelected(true);
        lbl.setIcon(imageIcons[0]);

        add("North", panNorth);
        add("Center", lbl);

        int w = 500, h = 500;
        int[] location = CenterFrame.getLocation(w, h);
        setBounds(location[0], location[1], w, h);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RadioButtonTest();
    }

    ItemListener radioListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {

            // 선택 해제될 때도 이벤트가 발생하므로
            // 선택된 경우만 처리
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JRadioButton selectedRadio =
                        (JRadioButton) e.getSource();

                if (selectedRadio == radios[0]) {
                    lbl.setIcon(imageIcons[0]);
                } else if (selectedRadio == radios[1]) {
                    lbl.setIcon(imageIcons[1]);
                } else {
                    lbl.setIcon(imageIcons[2]);
                }
            }
        }
    };
}
