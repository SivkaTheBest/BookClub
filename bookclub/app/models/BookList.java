package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class BookList extends Model {
    @Id
    public Long id;

    @OneToMany(mappedBy = "bookList")
    public Set<BookListBook> bookListBooks = new HashSet<>();

    @Column
    public Date startDate;

    @Column
    public Date endDate;

    public static Finder<Long, BookList> find = new Finder<Long, BookList>(
            Long.class, BookList.class
    );

}
