package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "login")
    public String login;

    @Constraints.Min(6)
    @Column(name = "password")
    public String password;

    @OneToMany(mappedBy = "user")
    List<Rating> ratings;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );
}
