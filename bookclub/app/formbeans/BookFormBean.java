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
    public Double rating;
    public Integer userRating;

    public BookFormBean(Long id, String name, String author, Integer userRating, Double rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.userRating = userRating;
    }

    public static List<BookFormBean> from(List<Book> list, Rating userRating, Double rating) {
        List<BookFormBean> result = new ArrayList<>();

        for (Book book: list) {
            result.add(from(book, userRating, rating));
        }

        return  result;
    }

    public static List<BookFormBean> from(List<Book> list, User user) {
        List<BookFormBean> result = new ArrayList<>();

        for (Book book: list) {
            result.add(from(book, Rating.findByUserAndByBook(user, book), Rating.findBookTotalRating(book)));
        }

        return  result;
    }

    public static BookFormBean from (Book book, Rating userRating, Double rating) {
        Integer rating1 = userRating == null ? 0 : userRating.rating;
        BookFormBean formBean = new BookFormBean(book.id, book.name, book.author, rating1, rating);
        return  formBean;
    }
}
