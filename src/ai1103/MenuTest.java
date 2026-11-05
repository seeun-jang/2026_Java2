package ai1103;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MenuTest extends JFrame {

    private final MenuStyle style = new MenuStyle();
    private final File[] savedFiles = new File[3];

    public MenuTest() {
        setTitle("THE FILM FILES.  |  Scene Diary");
        setContentPane(style);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu sceneMenu = new JMenu("Scene");
        JMenu themeMenu = new JMenu("Theme");
        menuBar.add(fileMenu);
        menuBar.add(sceneMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);

        // 배운 방식: ActionListener와 switch-case로 메뉴 동작 구현
        ActionListener fileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "New":
                        if (confirmClear()) {
                            style.setCurrentNote("", "");
                            savedFiles[style.getPhotoIndex()] = null;
                            style.status.setText("●  New film note");
                        }
                        break;
                    case "Open":
                        openFile();
                        break;
                    case "Save":
                        saveFile();
                        break;
                    case "Clear":
                        if (confirmClear()) {
                            // 제목과 감상평을 동시에 지우고 장면별 임시 기록에도 반영
                            style.setCurrentNote("", "");
                            style.status.setText("●  Film title & review cleared");
                        }
                        break;
                    case "Close":
                        dispose();
                        break;
                }
            }
        };

        ActionListener themeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                style.setTheme(e.getActionCommand());
                style.status.setText("●  Theme changed");
            }
        };

        ActionListener sceneListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "Scene 01": style.changePhoto(0); break;
                    case "Scene 02": style.changePhoto(1); break;
                    case "Scene 03": style.changePhoto(2); break;
                }
            }
        };

        addItem(fileMenu, "New", fileListener);
        addItem(fileMenu, "Open", fileListener);
        addItem(fileMenu, "Save", fileListener);
        addItem(fileMenu, "Clear", fileListener);
        fileMenu.addSeparator();
        addItem(fileMenu, "Close", fileListener);

        addItem(sceneMenu, "Scene 01", sceneListener);
        addItem(sceneMenu, "Scene 02", sceneListener);
        addItem(sceneMenu, "Scene 03", sceneListener);

        addItem(themeMenu, "Ivory", themeListener);
        addItem(themeMenu, "Sage", themeListener);
        addItem(themeMenu, "Blush", themeListener);

        style.addToolbar(fileListener, themeListener);

        setSize(1060, 900);
        setMinimumSize(new Dimension(970, 790));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addItem(JMenu menu, String name, ActionListener listener) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        menu.add(item);
    }

    private boolean confirmClear() {
        if (style.textArea.getText().trim().isEmpty()
                && style.filmTitle.getText().trim().isEmpty()) {
            return true;
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                "현재 장면의 내용을 지울까요?\n저장하지 않은 내용은 사라집니다.",
                "메모 확인",
                JOptionPane.YES_NO_OPTION
        );
        return result == JOptionPane.YES_OPTION;
    }

    // 현재 장면에 대한 TXT 파일 열기
    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        try {
            File file = chooser.getSelectedFile();
            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            String title = "";
            String review = content;

            // 이 프로그램에서 저장한 파일이면 영화 제목도 복원
            if (content.startsWith("FILM: ")) {
                int divider = content.indexOf("\n---\n");
                if (divider >= 0) {
                    title = content.substring(6, content.indexOf('\n'));
                    review = content.substring(divider + 5);
                }
            }

            style.setCurrentNote(title, review);
            savedFiles[style.getPhotoIndex()] = file;
            style.status.setText("●  Opened: " + file.getName());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "파일을 열 수 없습니다.");
        }
    }

    // 현재 장면의 제목과 감상평을 TXT 파일로 저장
    private void saveFile() {
        int index = style.getPhotoIndex();
        File file = savedFiles[index];

        if (file == null) {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("scene_0" + (index + 1) + "_review.txt"));
            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

            file = chooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }

            if (file.exists()) {
                int result = JOptionPane.showConfirmDialog(
                        this, "기존 파일을 덮어쓸까요?", "저장 확인",
                        JOptionPane.YES_NO_OPTION
                );
                if (result != JOptionPane.YES_OPTION) return;
            }
        }

        try {
            String content = "FILM: " + style.filmTitle.getText()
                    + "\nSCENE: 0" + (index + 1) + "\n---\n"
                    + style.textArea.getText();

            Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
            savedFiles[index] = file;
            style.status.setText("●  Saved: " + file.getName());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "파일을 저장할 수 없습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuTest();
            }
        });
    }
}
