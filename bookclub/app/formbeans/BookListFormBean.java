package formbeans;

import models.BookList;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BookListFormBean {
    public Date startDate;
    public Date endDate;
    public List<Long> bookIds;

    public BookListFormBean(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static BookListFormBean from(BookList list) {
        BookListFormBean bookList = new BookListFormBean(list.startDate, list.endDate);
        bookList.bookIds = new LinkedList<>();
        bookList.bookIds.addAll(list.bookListBooks.stream().map(bookRef -> bookRef.book.id).collect(Collectors.toList()));

        return bookList;
    }
}
