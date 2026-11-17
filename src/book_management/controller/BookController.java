
package book_management.controller;

import book_management.domain.Book;
import book_management.repository.BookRepository;
import book_management.view.BookSearchPan;
import book_management.view.center.SetFrame;

import javax.swing.*;
import java.util.ArrayList;

public class BookController extends JFrame {

    JTabbedPane tabbedPane =
            new JTabbedPane(JTabbedPane.TOP);

    BookRepository repository;
    ArrayList<Book> bookList;
    JComboBox<String> combo;

    public BookController() {

        // 도서 검색 패널 생성
        BookSearchPan searchPan = new BookSearchPan();

        tabbedPane.addTab("도서 검색", searchPan);

        // 검색 조건
        combo = searchPan.getCombo();

        // Repository 생성
        repository = new BookRepository();

        // 최초 도서 목록 조회
        bookList = repository.select(
                searchPan.getSearchWord(),
                combo.getSelectedIndex()
        );

        // 조회 결과 전달
        searchPan.setBookList(bookList);
        searchPan.initView();

        // 검색 버튼 이벤트
        searchPan.getBtnSearch().addActionListener(e -> {

            bookList = repository.select(
                    searchPan.getSearchWord(),
                    combo.getSelectedIndex()
            );

            searchPan.setBookList(bookList);
            searchPan.putSearchResult();
        });

        add(tabbedPane);

        SetFrame.setFrame(
                this,
                "도서관리 시스템",
                700,
                600
        );
    }

    public static void main(String[] args) {
        new BookController();
    }
}
