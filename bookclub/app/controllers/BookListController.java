package controllers;

import com.google.gson.Gson;
import formbeans.BookListsFormBean;
import models.BookList;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class BookListController extends Controller {

    public static Result getBookLists() {
        Gson gson = new Gson();
        User user = User.find.byId(session().get("login"));

        BookListsFormBean bookLists = BookListsFormBean.from(BookList.find.all(), user);

        return ok(gson.toJson(bookLists));
    }


}
