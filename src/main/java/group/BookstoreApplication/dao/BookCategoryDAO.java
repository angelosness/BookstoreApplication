package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryDAO extends JpaRepository<BookCategory, Integer> {
    public BookCategory findByName(String name);
}
