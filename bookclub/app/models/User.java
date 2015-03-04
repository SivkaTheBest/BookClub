package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User extends Model {
    @Id
    @Column(name = "login")
    public String login;

    @Constraints.Min(6)
    @Column(name = "password")
    public String password;

    @OneToMany(mappedBy = "user")
    public List<Rating> ratings = new LinkedList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User authenticate(String login, String password) {
        return User.find.where().eq("login", login).eq("password", password).findUnique();
    }

    public static User findByLogin(String login) {
        return User.find.where().eq("login", login).findUnique();
    }
    
    public static Finder<String, User> find = new Finder<String, User>(
            String.class, User.class
    );
}
