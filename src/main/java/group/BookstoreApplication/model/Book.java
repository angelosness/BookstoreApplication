package group.BookstoreApplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User offeringUser;

    @Column(name="title")
    private String title;

    // the authors are automatically added/updated when we add a book
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(                         // creates book written by author relationship
        name="writes_book",
        joinColumns = @JoinColumn(name="book_id"),
        inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<BookAuthor> bookAuthors;

    // preloaded categories - no need to cascade persist op
    // respective category object is updated when we add a book
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="category_id")     // referenced column is always the primary key of the other table
    private BookCategory bookCategory;

    @Column(name="summary")
    private String summary;

    @ManyToMany
    @JoinTable(
            name="requests_book",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> requestingUsers;


    public Book() {
        super();
        bookAuthors = new ArrayList<BookAuthor>();
        requestingUsers = new ArrayList<User>();
    }

    public int getId() {
        return id;
    }

    public User getOfferingUser() {
        return offeringUser;
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

    public String getSummary() {
        return summary;
    }

    public List<User> getRequestingUsers() {
        return requestingUsers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOfferingUser(User offeringUser) {
        this.offeringUser = offeringUser;
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRequestingUsers(List<User> requestingUsers) {
        this.requestingUsers = requestingUsers;
    }
}
