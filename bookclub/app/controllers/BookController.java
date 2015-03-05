package controllers;

import com.google.gson.Gson;
import formbeans.BookFormBean;
import models.Book;
import models.User;
import play.mvc.*;

import java.util.List;

@Security.Authenticated(Secured.class)
public class BookController extends Controller {

    public static Result getBooks() {
        Gson gson = new Gson();

        User user = User.find.byId(session().get("login"));
        List<BookFormBean> list = BookFormBean.from(Book.find.all(), user);

        return ok(gson.toJson(list));
    }
}
