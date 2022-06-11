package model;

import java.util.Objects;

public class Book {
    private Integer bookId;
    private String bookName;
    private String author;
    private String description;
    private Integer pages;

    public Book( String bookName, String author, String description, Integer pages) {
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.pages = pages;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getName() {
        return bookName;
    }


    public String getAuthor() {
        return author;
    }


    public String getDescription() {
        return description;
    }


    public Integer getPages() {
        return pages;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(description, book.description) && Objects.equals(pages, book.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, author, description, pages);
    }

    @Override
    public String toString() {
        return "Book{" +
                " name='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                '}';
    }
}
