package group.BookstoreApplication.service.recommendations;

import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.ArrayList;

import java.util.List;

public class RecommendationsCategories extends TemplateRecommendationsStrategy{

    public List<Book> filterBook(User user){
        return bookRepository.findByStatusAndBookCategoryIn("AVAILABLE", user.getFavoriteCategories());
    }
}
