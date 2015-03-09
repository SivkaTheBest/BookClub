package formbeans;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFormBean {

    public String login;

    public UserFormBean(String login) {
        this.login = login;
    }

    public static List<UserFormBean> from(List<User> list) {
        List<UserFormBean> result = list.stream().map(UserFormBean::from).collect(Collectors.toList());

        return  result;
    }

    public static UserFormBean from (User user) {
        UserFormBean formBean = new UserFormBean(user.login);
        return  formBean;
    }


}
