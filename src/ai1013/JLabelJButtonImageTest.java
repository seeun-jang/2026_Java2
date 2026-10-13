
package ai1013;

import center.CenterFrame;
import javax.swing.*;
import java.awt.*;

public class JLabelJButtonImageTest extends JFrame {

    public JLabelJButtonImageTest() {
        setTitle("ImageIcon 설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon btnIcon = new ImageIcon("imgs/dogIcon.png");
        Image btnImg = btnIcon.getImage().getScaledInstance(
                25, 25, Image.SCALE_SMOOTH);
        btnIcon = new ImageIcon(btnImg);

        JButton btn = new JButton("사진 숨기기", btnIcon);
        btn.setPreferredSize(new Dimension(140, 40));

        JPanel panel = new JPanel();
        panel.add(btn);

        ImageIcon lblIcon = new ImageIcon("imgs/dog.png");
        Image lblImg = lblIcon.getImage().getScaledInstance(
                250, 190, Image.SCALE_SMOOTH);
        lblIcon = new ImageIcon(lblImg);

        JLabel lbl = new JLabel(lblIcon);
        lbl.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon finalLblIcon = lblIcon;

        btn.addActionListener(e -> {
            if (lbl.getIcon() != null) {
                lbl.setIcon(null);
                btn.setText("사진 보기");
            } else {
                lbl.setIcon(finalLblIcon);
                btn.setText("사진 숨기기");
            }
        });

        add("North", panel);
        add("Center", lbl);

        int w = 350, h = 300;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);

        setVisible(true);
    }

    public static void main(String[] args) {
        new JLabelJButtonImageTest();
    }
}
