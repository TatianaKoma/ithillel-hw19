package service;

import repository.BookRepository;
import repository.BookRepositoryDataBase;
import enums.Message;
import model.Book;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class BookService {
    private final BookRepository bookRepository;

//    public BookService(String path) {
//        this.bookRepository = new BookRepositoryFile(path);
//    }

    public BookService(Connection connection) {
        this.bookRepository = new BookRepositoryDataBase(connection);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public void save(Book book) {
        var isPresent = bookRepository.getAll().stream()
                .anyMatch(b -> areSame(b, book));
        if (!isPresent) {
            bookRepository.create(book);
            System.out.println(Message.ADD.getValue());
        } else {
            System.out.println(Message.NOT_ADD.getValue());
        }
    }

    public void delete(String nameOfBook) {
        var isPresent = bookRepository.getAll().stream()
                .anyMatch(b -> b.getName().equals(nameOfBook));

        if (isPresent) {
            bookRepository.delete(nameOfBook);
            System.out.println(Message.DELETE.getValue());
        } else {
            System.out.println(Message.NOT_EXISTS.getValue());
        }
    }

    private boolean areSame(Book b1, Book b2) {
        return Objects.equals(b1.getName(), b2.getName()) &&
                Objects.equals(b1.getAuthor(), b2.getAuthor()) &&
                Objects.equals(b1.getDescription(), b2.getDescription()) &&
                Objects.equals(b1.getPages(), b2.getPages());
    }
}
