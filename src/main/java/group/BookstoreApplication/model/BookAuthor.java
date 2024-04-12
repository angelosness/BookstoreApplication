package group.BookstoreApplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="book_authors")
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="author_name")
    private String authorName;

    @ManyToMany(mappedBy="bookAuthors")
    private List<Book> books;


    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
