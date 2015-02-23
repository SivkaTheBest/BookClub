package formbeans;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserFormBean {

    public String login;

    public UserFormBean(String login) {
        this.login = login;
    }

    public static List<UserFormBean> from(List<User> list) {
        List<UserFormBean> result = new ArrayList<>();

        for (User user: list) {
            result.add(from(user));
        }

        return  result;
    }

    public static UserFormBean from (User user) {
        UserFormBean formBean = new UserFormBean(user.login);
        return  formBean;
    }


}
