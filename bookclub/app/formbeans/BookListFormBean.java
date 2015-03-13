package formbeans;

import com.google.gson.annotations.Expose;
import models.BookList;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class BookListFormBean {
    public String startDate;
    public String endDate;
    public List<Long> bookIds;

    @Expose
    private static SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", new Locale("uk", "UA"));

    public BookListFormBean(Date startDate, Date endDate) {
        this.startDate = format.format(startDate);
        this.endDate = format.format(endDate);
    }

    public static BookListFormBean from(BookList list) {
        BookListFormBean bookList = new BookListFormBean(list.startDate, list.endDate);
        bookList.bookIds = new LinkedList<>();
        bookList.bookIds.addAll(list.bookListBooks.stream().map(bookRef -> bookRef.book.id).collect(Collectors.toList()));

        return bookList;
    }
}
