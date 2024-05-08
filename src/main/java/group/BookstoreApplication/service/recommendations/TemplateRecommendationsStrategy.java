package group.BookstoreApplication.service.recommendations;

import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public abstract class TemplateRecommendationsStrategy implements RecommendationsStrategy {

    @Autowired
    protected BookDAO bookRepository;

    //Defined Methods:
    @Override
    public List<Book> getRecommendations(User user, BookDAO bookRepository) {
        //Re-defining, in order to avoid the null values:
        this.bookRepository = bookRepository;

        List<Book> bookList = filterBook(user);
        List<Book> finalList = new ArrayList<Book>();

        for (Book book : bookList) {
            if (book.getOfferingUser().getId() != user.getId() && !(book.getRequestingUsers().contains(user))){
                finalList.add(book);
            }
        }

        //shuffle the results and limit by 10
        Collections.shuffle(finalList);

        if(finalList.size()>10) {
            finalList = finalList.subList(0,10);
        }

        return finalList;
    }

    //Abstract Methods:
    public abstract List<Book> filterBook(User user);


}
