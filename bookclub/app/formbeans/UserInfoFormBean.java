package formbeans;

import com.google.gson.annotations.Expose;
import models.Rating;
import models.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class UserInfoFormBean {

    public String login;
    public Integer total;
    public Integer positive;
    public Integer neutral;
    public Integer negative;
    public Double average;
    public Boolean isYou;

    @Expose
    private DecimalFormat df = new DecimalFormat("####0.00");

    public UserInfoFormBean(String login, int total, int positive, int neutral, int negative, double average, boolean isYou) {
        this.login = login;
        this.total = total;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
        this.average = round(average, 2);
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
