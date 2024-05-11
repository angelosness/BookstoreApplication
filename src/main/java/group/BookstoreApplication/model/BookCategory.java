package group.BookstoreApplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book_categories")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", unique=true)
    private String name;

    @OneToMany(mappedBy="bookCategory")       // mappedBy for the other side of the association
    private List<Book> books;


    public BookCategory() {
        super();
        books = new ArrayList<Book>();
    }

    public BookCategory(String name) {
        super();
        this.name = name;
        books = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
