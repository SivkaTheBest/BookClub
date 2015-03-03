package controllers;

import com.google.gson.Gson;
import formbeans.BookFormBean;
import models.Book;
import models.Rating;
import models.User;
import play.mvc.*;

import java.util.List;

@Security.Authenticated(Secured.class)
public class BookController extends Controller {

    public static Result getBooks() {
        Gson gson = new Gson();

        User user = User.findByLogin(session().get("login"));
        List<Rating> ratings = Rating.findByUser(user);
        List<BookFormBean> list = BookFormBean.from(Book.find.all());

        return ok(gson.toJson(list));
    }

    public static Result rateBook(Long id, Integer rating) {

        User user = User.findByLogin(session().get("login"));
        Book book = Book.find.byId(id);

        if(user == null || book == null || rating == null || rating < 1 || rating > 5) {
            return badRequest("Шось не так: ");
        } else {
            Rating bookRating = Rating.findByUserAndByBook(user, book);
            if(bookRating != null) {
                bookRating.rating = rating;
            } else {
                bookRating = new Rating(user, book, rating);
            }
            bookRating.save();
        }

        return ok("Рейтинг створено");
    }

}
