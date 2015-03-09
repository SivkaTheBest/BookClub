package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class BookListBook extends Model {
    @Id
    public Long id;

    @ManyToOne
    @JoinColumn(name = "bookList_id")
    public BookList bookList;

    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book book;

    public BookListBook(BookList bookList, Book book) {
        this.bookList = bookList;
        this.book = book;
    }

    public static Finder<Long, BookListBook> find = new Finder<Long, BookListBook>(
            Long.class, BookListBook.class
    );
}
