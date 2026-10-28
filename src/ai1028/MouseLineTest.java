
package ai1028;

import center.SetFrame;
import javax.swing.*;

public class MouseLineTest extends JFrame {

    public MouseLineTest() {
        add(new MouseLinePanel());

        SetFrame.setFrame(this, "마우스로 선 그리기", 500, 500);
    }

    public static void main(String[] args) {
        new MouseLineTest();
    }
}
