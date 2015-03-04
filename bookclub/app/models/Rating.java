package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Rating extends Model {

    @Id
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_login")
    public User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book book;

    @Constraints.Max(5)
    @Constraints.Min(1)
    public int rating;

    public Rating(User user, Book book, int rating) {
        this.user = user;
        this.book = book;
        this.rating = rating;
    }

    public static Rating findByUserAndByBook(User user, Book book) {
        return Rating.find.where().eq("user", user).eq("book", book).findUnique();
    }

    public static List<Rating> findByUser(User user) {
        return Rating.find.where().eq("user", user).findList();
    }

    public static Finder<Long, Rating> find = new Finder<Long, Rating>(
            Long.class, Rating.class
    );
}
