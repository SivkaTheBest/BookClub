package controllers;

import models.User;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static class Login {
        public String login;
        public String password;

        public String validate() {
            if (User.authenticate(login, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render());
    }

    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("login", loginForm.get().login);
            return redirect(routes.Application.index());
        }
    }
}
