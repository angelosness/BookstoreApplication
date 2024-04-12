package group.BookstoreApplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToMany
    @JoinTable(                         // creates book written by author relationship
        name="writes_book",
        joinColumns = @JoinColumn(name="book_id"),
        inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<BookAuthor> bookAuthors;

    @ManyToOne
    @JoinColumn(name="category_id")
    private BookCategory bookCategory;


    //private List<User> requestingUsers;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

//    public List<User> getRequestingUsers() {
//        return requestingUsers;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

//    public void setRequestingUsers(List<User> requestingUsers) {
//        this.requestingUsers = requestingUsers;
//    }
}
