package group.BookstoreApplication.service.search;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public abstract class TemplateSearchStrategy implements SearchStrategy{

    @Autowired
    protected BookDAO bookRepository;

    //Defined Methods:
    @Override
    public List<Book> search(SearchDTO searchData, BookDAO bookRepository){
        this.bookRepository=bookRepository;

        List<Book> finalList = new ArrayList<Book>();
        List<Book> initialList = makeInitialListOfBooks(searchData);

        for(Book currentBook : initialList)
        {
            if(checkIfAuthorsMatch(searchData,currentBook)) { finalList.add(currentBook); }
        }

        return finalList;
    }


    //Abstract Methods:
    public abstract List<Book> makeInitialListOfBooks(SearchDTO searchData);

    public abstract boolean checkIfAuthorsMatch(SearchDTO searchData, Book book);
}

