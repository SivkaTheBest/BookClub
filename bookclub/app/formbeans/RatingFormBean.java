package formbeans;

import models.Book;
import models.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RatingFormBean {

    public int rating;
    public Long bookId;
    public String userId;

    public RatingFormBean(int rating, Long bookId, String userId) {
        this.rating = rating;
        this.bookId = bookId;
        this.userId = userId;
    }

    public static List<RatingFormBean> from(List<Rating> list) {
        List<RatingFormBean> result = list.stream().map(RatingFormBean::from).collect(Collectors.toList());

        return  result;
    }

    public static RatingFormBean from(Rating rating) {
        return  new RatingFormBean(rating.rating, rating.book.id, rating.user.login);
    }
}
