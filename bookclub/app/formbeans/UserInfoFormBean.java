package formbeans;

import models.Rating;
import models.User;

public class UserInfoFormBean {

    public String login;
    public Integer total;
    public Integer positive;
    public Integer neutral;
    public Integer negative;
    public Double average;
    public Boolean isYou;

    public UserInfoFormBean(String login, int total, int positive, int neutral, int negative, double average, boolean isYou) {
        this.login = login;
        this.total = total;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
        this.average = average;
        this.isYou = isYou;
    }

    public static UserInfoFormBean from (User user, User current) {
        int positive = 0;
        int neutral = 0;
        int negative = 0;
        double average = 0;

        for(Rating rating :  user.ratings) {
            if(rating.rating <= 2)
                negative++;
            if(rating.rating == 3)
                neutral++;
            if(rating.rating >= 4)
                positive++;
            average += rating.rating;
        }
        if(user.ratings.size() != 0)
            average = average / user.ratings.size();

        return  new UserInfoFormBean(user.login, user.ratings.size(),
                positive, neutral, negative, average, current.login == user.login);
    }
}
