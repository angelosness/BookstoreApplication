package group.BookstoreApplication.service.recommendations;

import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsAuthorsAndCategories extends TemplateRecommendationsStrategy{

    public List<Book> filterBook(User user){
        List<String> authorList = new ArrayList<String>();

        if(user.getFavoriteAuthors() != null) {
            authorList = List.of(user.getFavoriteAuthors().split("\\s*,\\s*"));
        }

        return bookRepository.findByStatusAndBookCategoryInOrBookAuthorsNameIn("AVAILABLE", user.getFavoriteCategories(), authorList);
    }
}
