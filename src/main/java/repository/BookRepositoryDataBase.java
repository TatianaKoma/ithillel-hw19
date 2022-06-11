package repository;

import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryDataBase implements BookRepository {
    private final Connection connection;

    public BookRepositoryDataBase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getAll() {
        var select = "SELECT * FROM book";
        PreparedStatement preparedStatement = null;
        List<Book> books = new ArrayList<>();
        ResultSet result;
        try {
            preparedStatement = connection.prepareStatement(select);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            while (result.next()) {
                books.add(new Book(
                        result.getString("book_name"),
                        result.getString("author"),
                        result.getString("description"),
                        result.getInt("pages")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void create(Book book) {
        String SQL = "INSERT INTO book( book_name, author, description, pages)" + " VALUES (?,?,?,?);";
        String book_name = book.getName();
        String author = book.getAuthor();
        String description = book.getDescription();
        int pages = book.getPages();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, book_name);
            pstmt.setString(2, author);
            pstmt.setString(3, description);
            pstmt.setInt(4, pages);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(String bookName) {
        String SQL = "DELETE FROM book WHERE book_name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, bookName);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
