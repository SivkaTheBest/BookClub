package formbeans;

import models.Book;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class BookFormBean {

    public Long id;
    public String name;
    public String author;

    public BookFormBean(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public static List<BookFormBean> from(List<Book> list) {
        List<BookFormBean> result = new ArrayList<>();

        for (Book book: list) {
            result.add(from(book));
        }

        return  result;
    }

    public static BookFormBean from (Book book) {
        BookFormBean formBean = new BookFormBean(book.id, book.name, book.author);
        return  formBean;
    }


}
