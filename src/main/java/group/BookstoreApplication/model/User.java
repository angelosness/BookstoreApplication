package group.BookstoreApplication.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        //for auto increment
    @Column(name="id")
    private int id;

    @Column(name="username", unique=true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    // app related fields
    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="favorite_authors")
    private String favoriteAuthors;

    @ManyToMany
    @JoinTable(
        name="favorite_categories",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="category_id"))
    private List<BookCategory> favoriteCategories;

    // orphanRemoval for removing books that have been deleted from db
    @OneToMany(mappedBy="offeringUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> bookOffers;

    @ManyToMany(mappedBy="requestingUsers")
    private List<Book> requestedBooks;


    public User() {
        super();
        favoriteCategories = new ArrayList<BookCategory>();
        bookOffers = new ArrayList<Book>();
        requestedBooks = new ArrayList<Book>();
    }

    public User(int id) {
        super();
        this.id = id;
        favoriteCategories = new ArrayList<BookCategory>();
        bookOffers = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public List<BookCategory> getFavoriteCategories() {
        return favoriteCategories;
    }

    public List<Book> getBookOffers() {
        return bookOffers;
    }

    public List<Book> getRequestedBooks() {
        return requestedBooks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFavoriteAuthors(String favoriteAuthors) {
        this.favoriteAuthors = favoriteAuthors;
    }

    public void setFavoriteCategories(List<BookCategory> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public void setBookOffers(List<Book> bookOffers) {
        this.bookOffers = bookOffers;
    }

    public void setRequestedBooks(List<Book> requestedBooks) {
        this.requestedBooks = requestedBooks;
    }

    // for UserDetails
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}