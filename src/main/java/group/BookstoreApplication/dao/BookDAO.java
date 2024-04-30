package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

    public List<Book> findByTitle(String title);

    public List<Book> findByTitleContaining(String title);

=======
@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

>>>>>>> Stashed changes
}
