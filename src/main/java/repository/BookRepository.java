package repository;

import model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
    void create(Book book);
    void delete(String name);

}
