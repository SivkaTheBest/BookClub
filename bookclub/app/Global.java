import models.Book;
import models.Rating;
import models.User;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Map data = (Map)Yaml.load("initial-data.yml");

        Ebean.delete(User.find.all());
        Ebean.delete(Book.find.all());
        Ebean.delete(Rating.find.all());

        Ebean.save((Collection) (data.get("users")));
        Ebean.save((Collection) (data.get("books")));
    }
}