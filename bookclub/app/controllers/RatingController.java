package controllers;


import com.google.gson.Gson;
import formbeans.BookFormBean;
import formbeans.RatingFormBean;
import models.Book;
import models.Rating;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

@Security.Authenticated(Secured.class)
public class RatingController extends Controller {

    public static Result rateBook(Long bookId, int userRating) {

        Book book = Book.find.byId(bookId);
        User user = User.find.byId(session().get("login"));
        Rating rating = Rating.findByUserAndByBook(user, book);

        if(!(user == null || book == null || userRating < 1 || userRating > 5)) {

            if(rating == null) {
                rating = new Rating(user, book, userRating);

                book.ratings.add(rating);
                user.ratings.add(rating);

                book.save();
                user.save();
            } else {
                rating.rating = userRating;
            }

            rating.save();

            Gson gson = new Gson();
            BookFormBean bookFormBean = BookFormBean.from(book, Rating.findByUserAndByBook(user, book), Rating.findBookTotalRating(book));
            return ok(gson.toJson(bookFormBean));
        }

        return badRequest();
    }

    public static Result deleteRating(Long bookId) {
        Book book = Book.find.byId(bookId);
        User user = User.find.byId(session().get("login"));

        if(user != null && book != null) {
            Rating rating = Rating.findByUserAndByBook(user, book);
            if(rating != null) {
                book.ratings.remove(rating);
                user.ratings.remove(rating);
                rating.delete();

                Gson gson = new Gson();
                BookFormBean bookFormBean = BookFormBean.from(book, Rating.findByUserAndByBook(user, book), Rating.findBookTotalRating(book));
                return ok(gson.toJson(bookFormBean));

            } else {
                return badRequest();
            }
        }

        return badRequest();
    }

    public static Result ratings() {

        User user = User.find.byId(session().get("login"));
        Gson gson = new Gson();

        if(user != null) {
            List<Rating> ratings = Rating.findByUser(user);
            return ok(gson.toJson(RatingFormBean.from(ratings)));
        }

        return badRequest();
    }
}
