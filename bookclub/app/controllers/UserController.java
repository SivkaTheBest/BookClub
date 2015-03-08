package controllers;

import com.google.gson.Gson;
import formbeans.UserFormBean;
import formbeans.UserInfoFormBean;
import models.User;
import play.mvc.*;

import java.util.List;

@Security.Authenticated(Secured.class)
public class UserController  extends Controller {

    public static Result getUsers() {
        Gson gson = new Gson();
        List<UserFormBean> list = UserFormBean.from(User.find.all());

        return ok(gson.toJson(list));
    }

    public static Result getUserInfo(String login) {
        User user = User.findByLogin(login);
        User currentUser = User.find.byId(session().get("login"));

        if(user != null) {
            Gson gson = new Gson();
            UserInfoFormBean userInfoFormBean = UserInfoFormBean.from(user, currentUser);
            return ok(gson.toJson(userInfoFormBean));
        } else {
            return badRequest();
        }
    }

}
