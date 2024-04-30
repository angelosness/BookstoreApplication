package group.BookstoreApplication.service.search;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;

import java.util.List;

public class ExactSearchStrategy extends TemplateSearchStrategy {

    public List<Book> makeInitialListOfBooks(SearchDTO searchData){

        return bookRepository.findByTitle(searchData.getTitle());
    }

    public boolean checkIfAuthorsMatch(SearchDTO searchData, Book book){

        List<BookAuthor> authorList = book.getBookAuthors();
        List<String> authorSearchList = List.of(searchData.getAuthors().split("\\s*,\\s*"));

        for(BookAuthor author : authorList){ if(!(authorSearchList.contains(author.getName()))) { return false; } }

        return true;
    }
}
