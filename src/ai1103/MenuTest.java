
package ai1103;

import center.SetFrame;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MenuTest extends JFrame implements MouseListener {

    private final MenuStyle style = new MenuStyle();
    private final JTextArea textArea = style.textArea;
    private File currentFile;

    public MenuTest() {
        setContentPane(style);
        textArea.addMouseListener(this);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu themeMenu = new JMenu("Theme");

        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);

        // File 메뉴와 툴바가 같은 리스너 사용
        ActionListener fileListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                switch (e.getActionCommand()) {
                    case "New":
                        textArea.setText("");
                        currentFile = null;
                        style.setTheme("Mint");
                        break;

                    case "Open":
                        openFile();
                        break;

                    case "Save":
                        saveFile();
                        break;

                    case "Clear":
                        textArea.setText("");
                        style.status.setText("메모를 지웠습니다.");
                        break;

                    case "Close":
                        dispose();
                        break;
                }
            }
        };

        // 교수님 방식: ActionListener + switch-case
        ActionListener themeListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                switch (e.getActionCommand()) {
                    case "Mint":
                        style.setTheme("Mint");
                        break;

                    case "Pink":
                        style.setTheme("Pink");
                        break;

                    case "Snow":
                        style.setTheme("Snow");
                        break;
                }
            }
        };

        // 메뉴 등록
        addItem(fileMenu, "New", fileListener);
        addItem(fileMenu, "Open", fileListener);
        addItem(fileMenu, "Save", fileListener);
        addItem(fileMenu, "Clear", fileListener);
        fileMenu.addSeparator();
        addItem(fileMenu, "Close", fileListener);

        addItem(themeMenu, "Mint", themeListener);
        addItem(themeMenu, "Pink", themeListener);
        addItem(themeMenu, "Snow", themeListener);

        // 툴바에도 리스너 연결
        style.addToolbar(fileListener, themeListener);

        SetFrame.setFrame(this, "Christmas Memo", 660, 720);
        setMinimumSize(new java.awt.Dimension(570, 630));
        setLocationRelativeTo(null);
    }

    // 메뉴 항목 추가
    private void addItem(JMenu menu, String name,
                         ActionListener listener) {

        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        menu.add(item);
    }

    // 파일 열기
    private void openFile() {

        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {

            try {
                File file = chooser.getSelectedFile();

                textArea.setText(
                        Files.readString(
                                file.toPath(),
                                StandardCharsets.UTF_8
                        )
                );

                currentFile = file;
                style.status.setText("열기: " + file.getName());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this, "파일을 열 수 없습니다."
                );
            }
        }
    }

    // 파일 저장
    private void saveFile() {

        File file = currentFile;

        if (file == null) {
            JFileChooser chooser = new JFileChooser();

            if (chooser.showSaveDialog(this)
                    != JFileChooser.APPROVE_OPTION) {
                return;
            }

            file = chooser.getSelectedFile();

            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }

            if (file.exists() &&
                    JOptionPane.showConfirmDialog(
                            this,
                            "기존 파일을 덮어쓸까요?",
                            "저장 확인",
                            JOptionPane.YES_NO_OPTION
                    ) != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            Files.writeString(
                    file.toPath(),
                    textArea.getText(),
                    StandardCharsets.UTF_8
            );

            currentFile = file;
            style.status.setText("저장: " + file.getName());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this, "파일을 저장할 수 없습니다."
            );
        }
    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        style.status.setText(
                "메모 영역 클릭: " + e.getX() + ", " + e.getY()
        );
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuTest::new);
    }
}
