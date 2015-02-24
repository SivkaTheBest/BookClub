package controllers;

import com.google.gson.Gson;
import formbeans.BookFormBean;
import models.Book;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class BookController extends Controller {

    public static Result getBooks() {
        Gson gson = new Gson();
        List<BookFormBean> list = BookFormBean.from(Book.find.all());

        return ok(gson.toJson(list));
    }

    public static Result rateBook(Long id, Integer rating) {

        return notFound();
    }
}
