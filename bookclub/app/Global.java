import play.*;
import play.db.ebean.Model;
import play.libs.*;
import com.avaje.ebean.Ebean;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Map data = (Map)Yaml.load("initial-data.yml");

        Ebean.save((Collection)(data.get("users")));
        Ebean.save((Collection)(data.get("books")));
    }
}