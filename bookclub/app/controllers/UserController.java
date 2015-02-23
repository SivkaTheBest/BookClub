package controllers;

import com.google.gson.Gson;
import formbeans.UserFormBean;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class UserController  extends Controller {

    public static Result getUsers() {
        Gson gson = new Gson();
        List<UserFormBean> list = UserFormBean.from(User.find.all());

        return ok(gson.toJson(list));
    }

}
