
package book_management.view;

import book_management.domain.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchPan extends JPanel {

    JTextField fieldSearch = new JTextField(20);
    JButton btnSearch = new JButton("검색");

    String[] comboStr = {"도서명", "출판사명", "저자명"};
    JComboBox<String> combo = new JComboBox<>(comboStr);

    JTable table;
    DefaultTableModel model;

    String[] tableHeader = {
            "도서번호", "도서명", "저자명",
            "출판사", "도서가격", "재고량", "카테고리"
    };

    ArrayList<Book> bookList = new ArrayList<>();
    String searchWord;

    public BookSearchPan() {

        setLayout(new BorderLayout());

        JPanel panN = new JPanel();
        JLabel lbl = new JLabel("검색어: ");

        panN.add(lbl);
        panN.add(fieldSearch);
        panN.add(combo);
        panN.add(btnSearch);

        add("North", panN);
    }

    // 테이블 초기화
    public void initView() {

        model = new DefaultTableModel(tableHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        add("Center", scroll);

        putSearchResult();
    }

    // 검색 결과 출력
    public void putSearchResult() {

        model.setRowCount(bookList.size());

        for (int i = 0; i < bookList.size(); i++) {

            Book book = bookList.get(i);

            model.setValueAt(book.getIsbn(), i, 0);
            model.setValueAt(book.getName(), i, 1);
            model.setValueAt(book.getAuthor(), i, 2);
            model.setValueAt(book.getPublish_name(), i, 3);
            model.setValueAt(book.getPrice(), i, 4);
            model.setValueAt(book.getInventory(), i, 5);
            model.setValueAt(book.getCategory_name(), i, 6);
        }
    }

    // 도서 목록 설정
    public void setBookList(ArrayList<Book> bookList) {

        if (bookList == null) {
            this.bookList = new ArrayList<>();
        } else {
            this.bookList = bookList;
        }
    }

    // Getter
    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JComboBox<String> getCombo() {
        return combo;
    }

    public String getSearchWord() {
        searchWord = fieldSearch.getText().trim();
        return searchWord;
    }
}
