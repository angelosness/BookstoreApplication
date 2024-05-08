package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
    public Book findById(int id);

    public List<Book> findByTitle(String title);

    public List<Book> findByTitleContaining(String title);

    public List<Book> findByStatusAndBookAuthorsNameIn(String status, List<String> authors);

    public List<Book> findByStatusAndBookCategoryIn(String status, List<BookCategory> categories);

    public List<Book> findByStatusAndBookAuthorsNameInOrStatusAndBookCategoryIn(String status, List<String> authors, String status2, List<BookCategory> categories);
}
