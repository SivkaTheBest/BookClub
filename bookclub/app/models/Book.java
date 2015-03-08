package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
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
    public List<Rating> ratings = new LinkedList<>();

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public static Finder<Long, Book> find = new Finder<Long, Book>(
            Long.class, Book.class
    );

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (!author.equals(book.author)) return false;
        if (!name.equals(book.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
