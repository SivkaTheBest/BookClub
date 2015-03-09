package formbeans;

import models.Book;
import models.BookList;
import models.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookListsFormBean {
    public List<BookListFormBean> bookLists;
    public List<BookFormBean> books;

    public BookListsFormBean(List<BookFormBean> books, List<BookListFormBean> bookLists) {
        this.books = books;
        this.bookLists = bookLists;
    }

    public static BookListsFormBean from(List<BookList> bookLists, User user) {

        List<BookListFormBean> bookListsBeans = new LinkedList<>();
        List<BookFormBean> booksBeans = new LinkedList<>();

        bookListsBeans.addAll(bookLists.stream().map(BookListFormBean::from).collect(Collectors.toList()));

        Set<Book> books = new HashSet<>();
        for(BookList bookList: bookLists) {
            books.addAll(bookList.bookListBooks.stream().map(bookRef -> bookRef.book).collect(Collectors.toList()));
        }

        booksBeans.addAll(BookFormBean.from(books, user));

        BookListsFormBean result = new BookListsFormBean(booksBeans, bookListsBeans);

        return result;
    }
}
