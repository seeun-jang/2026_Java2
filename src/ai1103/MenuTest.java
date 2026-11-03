
package ai1103;

import center.SetFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MenuTest extends JFrame implements MouseListener {

    private JTextArea textArea;
    private JLabel statusLabel;
    private MenuStyle.NoteCard noteCard;

    public MenuTest() {
        setLayout(new BorderLayout());

        // =========================
        // 1. 메뉴 구성
        // =========================
        JMenuBar menuBar = MenuStyle.menuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = MenuStyle.menu("File");
        JMenu editMenu = MenuStyle.menu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        JMenuItem newItem = MenuStyle.item("New");
        JMenuItem openItem = MenuStyle.item("Open");
        JMenuItem clearItem = MenuStyle.item("Clear");
        JMenuItem closeItem = MenuStyle.item("Close");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(clearItem);
        fileMenu.addSeparator();
        fileMenu.add(closeItem);

        JMenuItem mintItem = MenuStyle.item("Mint");
        JMenuItem pinkItem = MenuStyle.item("Pink");
        JMenuItem creamItem = MenuStyle.item("Cream");

        editMenu.add(mintItem);
        editMenu.add(pinkItem);
        editMenu.add(creamItem);

        // =========================
        // 2. 화면 배치
        // =========================
        JPanel mainPanel = MenuStyle.mainPanel();
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(
                MenuStyle.headerPanel(),
                BorderLayout.NORTH
        );

        textArea = MenuStyle.textArea();
        noteCard = new MenuStyle.NoteCard(textArea);

        mainPanel.add(noteCard, BorderLayout.CENTER);

        statusLabel = MenuStyle.statusLabel(
                "A little note for today"
        );

        JButton clearButton = MenuStyle.clearButton();

        mainPanel.add(
                MenuStyle.footerPanel(statusLabel, clearButton),
                BorderLayout.SOUTH
        );

        // =========================
        // 3. ActionListener
        // =========================
        ActionListener backgroundListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Color color = null;

                switch (e.getActionCommand()) {

                    case "Mint":
                        color = MenuStyle.PASTEL_MINT;
                        break;

                    case "Pink":
                        color = MenuStyle.PASTEL_PINK;
                        break;

                    case "Cream":
                        color = MenuStyle.PASTEL_CREAM;
                        break;
                }

                if (color != null) {
                    noteCard.setPaperColor(color);
                    statusLabel.setText(
                            "Paper color: " + e.getActionCommand()
                    );
                }
            }
        };

        // 배경색 리스너 등록
        mintItem.addActionListener(backgroundListener);
        pinkItem.addActionListener(backgroundListener);
        creamItem.addActionListener(backgroundListener);

        // =========================
        // 4. 메모 관리
        // =========================

        // 전체 내용 삭제
        ActionListener clearListener = e -> {
            textArea.setText("");
            statusLabel.setText("Memo cleared");
            textArea.requestFocusInWindow();
        };

        clearItem.addActionListener(clearListener);
        clearButton.addActionListener(clearListener);

        // 새 메모 생성
        newItem.addActionListener(e -> {
            textArea.setText("");
            noteCard.setPaperColor(MenuStyle.PASTEL_CREAM);
            statusLabel.setText("New memo");
            textArea.requestFocusInWindow();
        });

        // 파일 열기
        openItem.addActionListener(e -> openFile());

        // 종료
        closeItem.addActionListener(e -> dispose());

        // =========================
        // 5. MouseListener 등록
        // =========================
        textArea.addMouseListener(this);

        SetFrame.setFrame(
                this, "Christmas Memo", 620, 720
        );

        setMinimumSize(new Dimension(510, 600));
        setLocationRelativeTo(null);
    }

    // =========================
    // 파일 열기
    // =========================
    private void openFile() {

        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {

            try {
                String contents = Files.readString(
                        chooser.getSelectedFile().toPath(),
                        StandardCharsets.UTF_8
                );

                textArea.setText(contents);

                statusLabel.setText(
                        "Opened: " + chooser.getSelectedFile().getName()
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this, "파일을 열 수 없습니다."
                );
            }
        }
    }

    // =========================
    // MouseListener
    // =========================
    @Override
    public void mouseClicked(MouseEvent e) {
        statusLabel.setText(
                "Clicked: " + e.getX() + ", " + e.getY()
        );
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        statusLabel.setText(
                "Write your little Christmas note"
        );
    }

    @Override
    public void mouseExited(MouseEvent e) {
        statusLabel.setText(
                "A little note for today"
        );
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // =========================
    // Main
    // =========================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuTest::new);
    }
}
