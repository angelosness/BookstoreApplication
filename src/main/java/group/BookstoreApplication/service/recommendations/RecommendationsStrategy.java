package group.BookstoreApplication.service.recommendations;

import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.User;

import java.util.List;

public interface RecommendationsStrategy {

    public List<Book> getRecommendations(User user, BookDAO bootRepository);
}


