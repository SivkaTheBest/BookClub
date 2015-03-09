import models.*;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Map data = (Map)Yaml.load("initial-data.yml");

        Ebean.delete(BookListBook.find.all());
        Ebean.delete(BookList.find.all());
        Ebean.delete(Rating.find.all());
        Ebean.delete(User.find.all());
        Ebean.delete(Book.find.all());

        Ebean.save((Collection) (data.get("users")));
        Ebean.save((Collection) (data.get("books")));
        Ebean.save((Collection) (data.get("ratings")));
        Ebean.save((Collection) (data.get("lists")));
        Ebean.save((Collection) (data.get("bookListBook")));
    }
}