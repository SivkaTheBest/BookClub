package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id
    public Long id;

    public String login;

    @Constraints.Min(6)
    public String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );
}
