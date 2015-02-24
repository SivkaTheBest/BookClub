package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating extends Model {

    @Id
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "login")
    public User user;

    @ManyToOne
    @JoinColumn(name = "book", referencedColumnName = "id")
    public Book book;

    @Constraints.Max(10)
    public int rating;

    public static Finder<String, Rating> find = new Finder<String, Rating>(
            String.class, Rating.class
    );
}
