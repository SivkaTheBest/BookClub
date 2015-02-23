package controllers;

import models.Book;
import models.User;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(User.find.all(), Book.find.all()));
    }

}
