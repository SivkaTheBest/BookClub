package controllers;


import models.Book;
import models.Rating;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class RatingController extends Controller {

    public static Result rateBook(Long bookId, int userRating) {

        Book book = Book.find.byId(bookId);
        User user = User.find.byId(session().get("login"));

        if(!(user == null || book == null || userRating == 0)) {
            Rating rating = new Rating(user, book, userRating);

            book.ratings.add(rating);
            user.ratings.add(rating);

            rating.save();
        } else {
            badRequest();
        }

        return ok();
    }
}
