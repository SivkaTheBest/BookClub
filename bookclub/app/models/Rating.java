package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rating extends Model {

    @Id
    public Long id;

    public User user;
    public Book book;

    @Constraints.Max(10)
    public int rating;


}
