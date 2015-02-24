package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Book extends Model {

    @Id
    public Long id;

    @Constraints.Max(256)
    public String name;

    @Constraints.Max(256)
    public String author;

    @OneToMany(mappedBy = "book")
    List<Rating> ratings;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public static Finder<Long, Book> find = new Finder<Long, Book>(
            Long.class, Book.class
    );

}
