package formbeans;

import models.Book;
import models.Rating;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class BookFormBean {

    public Long id;
    public String name;
    public String author;
    public Integer rating;
    public Integer userRating;

    public BookFormBean(Long id, String name, String author, Integer userRating, Integer rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.userRating = userRating;
    }

    public static List<BookFormBean> from(List<Book> list, Rating userRating, Integer rating) {
        List<BookFormBean> result = new ArrayList<>();

        for (Book book: list) {
            result.add(from(book, userRating, rating));
        }

        return  result;
    }

    public static List<BookFormBean> from(List<Book> list) {
        List<BookFormBean> result = new ArrayList<>();

        for (Book book: list) {
            result.add(from(book));
        }

        return  result;
    }

    public static BookFormBean from (Book book, Rating userRating, Integer rating) {
        BookFormBean formBean = new BookFormBean(book.id, book.name, book.author, userRating.rating, rating);
        return  formBean;
    }

    public static BookFormBean from (Book book) {
        BookFormBean formBean = new BookFormBean(book.id, book.name, book.author, 0, 0);
        return  formBean;
    }

}
