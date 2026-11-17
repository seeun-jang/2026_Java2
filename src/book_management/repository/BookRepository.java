
package book_management.repository;

import book_management.domain.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookRepository {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:XE";

    private static final String USER = "C##AISOFT";

    private static final String PASSWORD = "1234";


    public ArrayList<Book> select(String searchWord, int searchType) {

        ArrayList<Book> bookList = new ArrayList<>();

        String sql =
                "SELECT b.ISBN, b.NAME, b.AUTHOR, " +
                        "b.PRICE, b.INVENTORY, " +
                        "p.PUBLISH_NAME, c.CATEGORY_NAME " +
                        "FROM BOOK b " +
                        "LEFT JOIN PUBLISH p " +
                        "ON b.PUBLISH_ID = p.PUBLISH_ID " +
                        "LEFT JOIN CATEGORY c " +
                        "ON b.CATEGORY_ID = c.CATEGORY_ID ";

        // 검색 조건
        if (searchType == 0) {
            sql += "WHERE b.NAME LIKE ?";
        } else if (searchType == 1) {
            sql += "WHERE p.PUBLISH_NAME LIKE ?";
        } else {
            sql += "WHERE b.AUTHOR LIKE ?";
        }

        if (searchWord == null) {
            searchWord = "";
        }

        try (
                Connection conn = DriverManager.getConnection(
                        URL, USER, PASSWORD
                );
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, "%" + searchWord + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Book book = new Book(
                            rs.getString("ISBN"),
                            rs.getString("NAME"),
                            rs.getString("AUTHOR"),
                            rs.getString("PUBLISH_NAME"),
                            rs.getInt("PRICE"),
                            rs.getInt("INVENTORY"),
                            rs.getString("CATEGORY_NAME")
                    );

                    bookList.add(book);
                }
            }

        } catch (SQLException e) {
            System.out.println("도서 검색 오류");
            e.printStackTrace();
        }

        return bookList;
    }
}
