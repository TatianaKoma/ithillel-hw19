package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import model.Library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryFile implements BookRepository {
    private final File file;

    private static final ObjectMapper mapper = new ObjectMapper();

    public BookRepositoryFile(String path) {
        file = new File(path);
    }

    @Override
    public List<Book> getAll() {
        try {
            if (file.length() > 0) {
                return mapper.readValue(file, Library.class).getBooks();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void create(Book book) {
        try {
            Library library;
            if (file.length() > 0) {
                library = mapper.readValue(file, Library.class);
            } else {
                library = new Library();
            }
            library.getBooks().add(book);
            mapper.writeValue(file, library);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        try {
            Library library = mapper.readValue(file, Library.class);
            List<Book> books = library.getBooks().stream()
                    .filter(book -> !book.getName().equals(name))
                    .collect(Collectors.toList());
            library.setBooks(books);
            mapper.writeValue(file, library);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
