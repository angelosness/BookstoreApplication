package group.BookstoreApplication.service.search;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.model.Book;

import java.util.List;

public interface SearchStrategy {

    public List<Book> search(SearchDTO searchData, BookDAO bookRepository);

}
