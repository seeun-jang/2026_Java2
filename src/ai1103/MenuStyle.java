package ai1103;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;

public class MenuStyle extends JPanel {

    // MenuTest에서 사용하는 메모장 구성 요소
    public final DiaryArea textArea = new DiaryArea();
    public final JTextField filmTitle = new JTextField();
    public final JLabel status = new JLabel("●  Ready to write");

    // 사진마다 제목과 감상평을 잠시 보관
    private final String[] titles = {"", "", ""};
    private final String[] reviews = {"", "", ""};
    private int photoIndex = 0;

    private final JPanel header = new JPanel(new BorderLayout());
    private final JPanel toolbar = new JPanel(new BorderLayout());
    private final JPanel fileButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
    private final JPanel themeButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 7, 0));
    private final JButton[] themeDots = new JButton[3];
    private final JPanel gallery = new JPanel(new BorderLayout(18, 0));
    private final JPanel photoCard = new JPanel(new BorderLayout(0, 10));
    private final JPanel infoCard = new JPanel();
    private final JPanel diaryCard = new JPanel(new BorderLayout(0, 12));
    private final JPanel filmRow = new JPanel(new BorderLayout(12, 0));
    private final JPanel footer = new JPanel(new BorderLayout());

    private final PhotoCanvas photoLabel = new PhotoCanvas();
    private final JLabel counter = new JLabel("STILL 01 / 03");
    private final JLabel themeInfo = new JLabel("IVORY / THE FILM FILES");
    private final JLabel heading = new JLabel("THE FILM FILES.");
    private final JLabel subtitle = new JLabel("A PRIVATE ARCHIVE OF FRAMES & FEELINGS");
    private final JLabel galleryHeading = new JLabel("SCENE ARCHIVE");
    private final JLabel galleryDescription = new JLabel("Some scenes stay with us.");
    private final JLabel noteHeading = new JLabel("Dear cinema,");
    private final JLabel filmLabel = new JLabel("FILM TITLE");
    private final JLabel dateLabel = new JLabel(LocalDate.now().toString());

    private final JButton[] frameButtons = new JButton[3];
    // 안내 패널 문구
    private final JLabel guideTitle = new JLabel("HOW TO KEEP A FILM NOTE");
    private final JLabel guideOne = new JLabel("01   기억하고 싶은 장면을 고르세요.");
    private final JLabel guideTwo = new JLabel("02   영화 제목과 감상평을 적으세요.");
    private final JLabel guideThree = new JLabel("03   저장 버튼으로 TXT 파일을 남기세요.");
    private final JLabel guideFoot = new JLabel("장면별로 서로 다른 메모를 쓸 수 있어요.");

    private final JScrollPane scroll = new JScrollPane(textArea);

    private Color background;
    private Color cardColor;
    private Color paperColor;
    private Color ink;
    private Color muted;
    private Color accent;
    private Color borderColor;
    private String themeName = "Ivory";

    public MenuStyle() {
        setLayout(new BorderLayout(0, 17));
        setBorder(new EmptyBorder(21, 25, 14, 25));

        // 상단 제목
        JPanel titleArea = new JPanel(new BorderLayout(0, 3));
        titleArea.setOpaque(false);
        heading.setFont(new Font("Serif", Font.BOLD, 34));
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 10));
        titleArea.add(heading, BorderLayout.NORTH);
        titleArea.add(subtitle, BorderLayout.SOUTH);

        JLabel edition = new JLabel("VOL. 01   /   PERSONAL FILM JOURNAL");
        edition.setFont(new Font("Dialog", Font.PLAIN, 11));
        edition.setForeground(new Color(118, 113, 107));
        header.add(titleArea, BorderLayout.WEST);
        header.add(edition, BorderLayout.EAST);

        // 제목 아래에 파일 관리 및 테마 툴바 배치
        JPanel topArea = new JPanel(new BorderLayout(0, 15));
        topArea.setOpaque(false);
        topArea.add(header, BorderLayout.NORTH);
        toolbar.setOpaque(false);
        fileButtons.setOpaque(false);
        themeButtons.setOpaque(false);
        toolbar.add(fileButtons, BorderLayout.WEST);
        toolbar.add(themeButtons, BorderLayout.EAST);
        topArea.add(toolbar, BorderLayout.SOUTH);
        add(topArea, BorderLayout.NORTH);

        // 사진 영역: 가로로 길게 늘리지 않고, 종이 액자 가운데 배치
        photoLabel.setPreferredSize(new Dimension(495, 280));
        photoLabel.setToolTipText("오른쪽의 01, 02, 03 버튼으로 사진을 선택하세요.");
        JPanel photoHolder = new JPanel(new GridBagLayout());
        photoHolder.setOpaque(false);
        photoHolder.add(photoLabel);

        photoCard.add(photoHolder, BorderLayout.CENTER);
        JLabel photoCaption = new JLabel("MOMENTS ON FILM   /   PERSONAL SCENE COLLECTION");
        photoCaption.setFont(new Font("Dialog", Font.PLAIN, 10));
        photoCaption.setForeground(new Color(111, 108, 103));
        photoCard.add(photoCaption, BorderLayout.SOUTH);
        photoCard.setPreferredSize(new Dimension(625, 350));
        gallery.add(photoCard, BorderLayout.CENTER);

        // 오른쪽 아카이브 인덱스
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBorder(new EmptyBorder(14, 22, 14, 18));
        infoCard.setPreferredSize(new Dimension(326, 0));
        galleryHeading.setFont(new Font("Dialog", Font.BOLD, 11));
        galleryDescription.setFont(new Font("Serif", Font.ITALIC, 18));
        counter.setFont(new Font("Dialog", Font.BOLD, 12));
        galleryHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        galleryDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        counter.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoCard.add(galleryHeading);
        infoCard.add(Box.createVerticalStrut(10));
        infoCard.add(galleryDescription);
        infoCard.add(Box.createVerticalStrut(16));
        infoCard.add(counter);
        infoCard.add(Box.createVerticalStrut(11));

        JPanel frameRow = new JPanel(new GridLayout(1, 3, 8, 0));
        frameRow.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            final int number = i;
            JButton button = new JButton("0" + (i + 1));
            button.setFont(new Font("Dialog", Font.BOLD, 12));
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(51, 40));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changePhoto(number);
                }
            });
            frameButtons[i] = button;
            frameRow.add(button);
        }
        frameRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        frameRow.setPreferredSize(new Dimension(169, 40));
        frameRow.setMinimumSize(new Dimension(169, 40));
        frameRow.setMaximumSize(new Dimension(169, 40));
        infoCard.add(frameRow);
        infoCard.add(Box.createVerticalStrut(11));

        // 선택 방법 안내: 불필요한 이전/다음 버튼 대신 짧은 설명
        infoCard.add(Box.createVerticalStrut(13));
        JSeparator separator = new JSeparator();
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        separator.setForeground(new Color(213, 206, 197));
        separator.setBackground(new Color(213, 206, 197));
        separator.setMaximumSize(new Dimension(250, 1));
        infoCard.add(separator);
        infoCard.add(Box.createVerticalStrut(12));

        guideTitle.setFont(new Font("Dialog", Font.BOLD, 11));
        guideOne.setFont(new Font("Dialog", Font.PLAIN, 12));
        guideTwo.setFont(new Font("Dialog", Font.PLAIN, 12));
        guideThree.setFont(new Font("Dialog", Font.PLAIN, 12));
        guideFoot.setFont(new Font("Dialog", Font.PLAIN, 11));
        for (JLabel line : new JLabel[]{guideTitle, guideOne, guideTwo, guideThree, guideFoot}) {
            line.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        infoCard.add(guideTitle);
        infoCard.add(Box.createVerticalStrut(12));
        infoCard.add(guideOne);
        infoCard.add(Box.createVerticalStrut(8));
        infoCard.add(guideTwo);
        infoCard.add(Box.createVerticalStrut(8));
        infoCard.add(guideThree);
        infoCard.add(Box.createVerticalGlue());
        infoCard.add(guideFoot);

        gallery.add(infoCard, BorderLayout.EAST);
        gallery.setPreferredSize(new Dimension(0, 350));

        // 하단 일기장
        noteHeading.setFont(new Font("Serif", Font.ITALIC, 27));
        filmLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        dateLabel.setFont(new Font("Dialog", Font.PLAIN, 11));

        filmTitle.setFont(new Font("Dialog", Font.PLAIN, 14));
        filmTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 205, 197)),
                new EmptyBorder(8, 10, 8, 10)));
        filmTitle.setToolTipText("영화 제목을 입력하세요.");

        JPanel filmInput = new JPanel(new BorderLayout(9, 0));
        filmInput.setOpaque(false);
        filmInput.add(filmLabel, BorderLayout.WEST);
        filmInput.add(filmTitle, BorderLayout.CENTER);
        filmInput.setPreferredSize(new Dimension(420, 38));
        filmRow.add(filmInput, BorderLayout.CENTER);
        filmRow.add(dateLabel, BorderLayout.EAST);
        filmRow.setOpaque(false);

        JPanel diaryTop = new JPanel(new BorderLayout(0, 12));
        diaryTop.setOpaque(false);
        diaryTop.add(noteHeading, BorderLayout.NORTH);
        diaryTop.add(filmRow, BorderLayout.SOUTH);
        diaryCard.add(diaryTop, BorderLayout.NORTH);

        textArea.setFont(new Font("Dialog", Font.PLAIN, 16));
        textArea.setMargin(new Insets(17, 26, 17, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setToolTipText("영화를 보고 느낀 점을 자유롭게 기록해 보세요.");
        scroll.setBorder(BorderFactory.createEmptyBorder());
        diaryCard.add(scroll, BorderLayout.CENTER);
        diaryCard.setBorder(new EmptyBorder(18, 20, 18, 20));

        JPanel mainArea = new JPanel(new BorderLayout(0, 18));
        mainArea.setOpaque(false);
        mainArea.add(gallery, BorderLayout.NORTH);
        mainArea.add(diaryCard, BorderLayout.CENTER);
        add(mainArea, BorderLayout.CENTER);

        // 하단 상태 정보
        status.setFont(new Font("Dialog", Font.PLAIN, 11));
        themeInfo.setFont(new Font("Dialog", Font.PLAIN, 11));
        footer.add(status, BorderLayout.WEST);
        footer.add(themeInfo, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);

        setTheme("Ivory");
        updatePhoto();
    }

    // 툴바: 왼쪽은 파일 기능, 오른쪽은 색상 아이콘
    public void addToolbar(ActionListener fileListener, ActionListener themeListener) {
        fileButtons.removeAll();
        themeButtons.removeAll();

        addFileButton("＋  새 메모", "New", fileListener);
        addFileButton("▤  열기", "Open", fileListener);
        addFileButton("▣  저장", "Save", fileListener);
        addFileButton("×  지우기", "Clear", fileListener);

        JLabel themeLabel = new JLabel("THEME");
        themeLabel.setFont(new Font("Dialog", Font.BOLD, 10));
        themeLabel.setForeground(muted);
        themeButtons.add(themeLabel);

        String[] names = {"Ivory", "Sage", "Blush"};
        Color[] colors = {
                new Color(217, 195, 153),
                new Color(136, 164, 138),
                new Color(215, 156, 162)
        };
        for (int i = 0; i < 3; i++) {
            JButton button = new JButton("●");
            button.setActionCommand(names[i]);
            button.setToolTipText(names[i]);
            button.setFont(new Font("Dialog", Font.BOLD, 23));
            button.setForeground(colors[i]);
            button.setPreferredSize(new Dimension(34, 36));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.addActionListener(themeListener);
            themeDots[i] = button;
            themeButtons.add(button);
        }
        updateThemeDots();
        toolbar.revalidate();
        toolbar.repaint();
    }

    // 모든 파일 버튼 같은 색으로 통일
    private void addFileButton(String label, String command, ActionListener listener) {
        JButton button = new JButton(label);
        button.setActionCommand(command);
        button.setToolTipText(command);
        button.setFont(new Font("Dialog", Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(cardColor);
        button.setForeground(ink);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(10, 13, 10, 13)));
        button.addActionListener(listener);
        fileButtons.add(button);
    }

    private void updateThemeDots() {
        for (int i = 0; i < 3; i++) {
            JButton button = themeDots[i];
            if (button != null) {
                String name = button.getActionCommand();
                button.setBorder(BorderFactory.createLineBorder(
                        name.equals(themeName) ? ink : background,
                        1));
            }
        }
    }

    public int getPhotoIndex() {
        return photoIndex;
    }

    public void nextPhoto() {
        changePhoto((photoIndex + 1) % 3);
    }

    public void previousPhoto() {
        changePhoto((photoIndex + 2) % 3);
    }

    // 장면마다 입력한 제목과 감상평을 보관
    public void changePhoto(int newIndex) {
        titles[photoIndex] = filmTitle.getText();
        reviews[photoIndex] = textArea.getText();

        photoIndex = newIndex;
        filmTitle.setText(titles[photoIndex]);
        textArea.setText(reviews[photoIndex]);
        textArea.setCaretPosition(0);
        updatePhoto();
        status.setText("●  Scene " + (photoIndex + 1) + " selected");
    }

    public void setCurrentNote(String title, String review) {
        filmTitle.setText(title);
        textArea.setText(review);
        titles[photoIndex] = title;
        reviews[photoIndex] = review;
    }

    private void updatePhoto() {
        String path = "images/scene" + (photoIndex + 1) + ".jpg";
        File imageFile = new File(path);

        if (!imageFile.exists()) {
            imageFile = new File("src/" + path);
        }

        try {
            photoLabel.setImage(ImageIO.read(imageFile));
        } catch (Exception ex) {
            photoLabel.setImage(null);
        }

        counter.setText("STILL 0" + (photoIndex + 1) + " / 03");
        themeInfo.setText("SCENE " + (photoIndex + 1) + "/3  ·  " + themeName.toUpperCase());
        updateFrameButtons();
    }

    private void updateFrameButtons() {
        if (accent == null) return;
        for (int i = 0; i < 3; i++) {
            boolean selected = (i == photoIndex);
            frameButtons[i].setBackground(selected ? accent : cardColor);
            frameButtons[i].setForeground(selected ? Color.WHITE : ink);
            frameButtons[i].setBorder(BorderFactory.createLineBorder(borderColor));
        }
    }

    public void setTheme(String name) {
        themeName = name;
        switch (name) {
            case "Sage":
                background = new Color(229, 234, 224);
                cardColor = new Color(246, 248, 242);
                paperColor = new Color(250, 250, 245);
                ink = new Color(48, 60, 50);
                muted = new Color(101, 117, 103);
                accent = new Color(86, 111, 90);
                borderColor = new Color(182, 193, 177);
                break;
            case "Blush":
                background = new Color(244, 233, 229);
                cardColor = new Color(255, 249, 245);
                paperColor = new Color(255, 252, 248);
                ink = new Color(79, 56, 57);
                muted = new Color(148, 111, 110);
                accent = new Color(154, 99, 101);
                borderColor = new Color(217, 187, 185);
                break;
            default:
                background = new Color(238, 234, 226);
                cardColor = new Color(255, 253, 248);
                paperColor = new Color(253, 251, 245);
                ink = new Color(47, 47, 42);
                muted = new Color(117, 112, 105);
                accent = new Color(67, 81, 70);
                borderColor = new Color(210, 205, 195);
                break;
        }

        setBackground(background);
        header.setBackground(background);
        footer.setBackground(background);
        gallery.setBackground(background);
        toolbar.setBackground(background);
        photoCard.setBackground(cardColor);
        infoCard.setBackground(cardColor);
        diaryCard.setBackground(cardColor);
        scroll.getViewport().setBackground(paperColor);
        textArea.setBackground(paperColor);
        textArea.setForeground(ink);
        textArea.setCaretColor(ink);
        textArea.setLineColor(borderColor);
        filmTitle.setBackground(paperColor);
        filmTitle.setForeground(ink);
        filmTitle.setCaretColor(ink);

        heading.setForeground(ink);
        subtitle.setForeground(muted);
        galleryHeading.setForeground(muted);
        galleryDescription.setForeground(ink);
        counter.setForeground(accent);
        noteHeading.setForeground(ink);
        filmLabel.setForeground(muted);
        dateLabel.setForeground(muted);
        status.setForeground(ink);
        themeInfo.setForeground(muted);
        themeInfo.setText("SCENE " + (photoIndex + 1) + "/3  ·  " + themeName.toUpperCase());
        guideTitle.setForeground(accent);
        guideOne.setForeground(ink);
        guideTwo.setForeground(ink);
        guideThree.setForeground(ink);
        guideFoot.setForeground(muted);
        photoLabel.setBackground(background);

        photoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 5, 5, borderColor),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor),
                        new EmptyBorder(16, 18, 14, 18))));
        infoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(20, 22, 18, 18)));
        diaryCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(18, 20, 18, 20)));
        updateFrameButtons();
        updateThemeDots();
        repaint();
    }

    // 사진 전체를 잘림 없이 화면에 표시 (비율 유지)
    private class PhotoCanvas extends JPanel {
        private BufferedImage image;

        public PhotoCanvas() {
            setOpaque(true);
        }

        public void setImage(BufferedImage newImage) {
            image = newImage;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) {
                g.setColor(Color.DARK_GRAY);
                g.drawString("사진을 찾을 수 없습니다.", 18, 30);
                return;
            }

            int w = getWidth();
            int h = getHeight();
            int iw = image.getWidth();
            int ih = image.getHeight();

            // Math.min을 사용하면 사진이 잘리지 않음
            double scale = Math.min((double) w / iw, (double) h / ih);
            int drawW = (int) (iw * scale);
            int drawH = (int) (ih * scale);
            int x = (w - drawW) / 2;
            int y = (h - drawH) / 2;

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(image, x, y, drawW, drawH, null);
        }
    }

    // 공책의 가로줄을 그리는 간단한 메모 영역
    public static class DiaryArea extends JTextArea {
        private Color lineColor = new Color(210, 205, 195);

        public void setLineColor(Color color) {
            lineColor = color;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(lineColor);
            int lineHeight = getFontMetrics(getFont()).getHeight();
            int y = getInsets().top + lineHeight + 2;
            while (y < getHeight()) {
                g.drawLine(12, y, getWidth() - 12, y);
                y = y + lineHeight;
            }
            super.paintComponent(g);
        }
    }
}
