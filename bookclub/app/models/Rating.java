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

    public static Double findBookTotalRating(Book book) {
        List<Rating> ratings = Rating.find.where().eq("book", book).findList();
        double totalRating = 0;

        if(ratings.size() > 0) {
            for (Rating rating : ratings) {
                totalRating += rating.rating;
            }

            return totalRating / ratings.size();
        } else {
            return totalRating;
        }
    }

    public static Finder<Long, Rating> find = new Finder<Long, Rating>(
            Long.class, Rating.class
    );

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rating rating1 = (Rating) o;

        if (rating != rating1.rating) return false;
        if (!book.equals(rating1.book)) return false;
        if (!user.equals(rating1.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + rating;
        return result;
    }
}
