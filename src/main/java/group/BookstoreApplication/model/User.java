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

    @ManyToMany
    @JoinTable(
        name="favorite_authors",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="author_id"))
    private List<BookAuthor> favoriteAuthors;

    @ManyToMany
    @JoinTable(
        name="favorite_categories",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="category_id"))
    private List<BookCategory> favoriteCategories;

    @OneToMany(mappedBy="offeringUser", cascade = CascadeType.ALL)     // if a book added to the list, create book entry
    private List<Book> bookOffers;


    public User() {
        super();
        favoriteAuthors = new ArrayList<BookAuthor>();
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

    public List<BookAuthor> getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public List<BookCategory> getFavoriteCategories() {
        return favoriteCategories;
    }

    public List<Book> getBookOffers() {
        return bookOffers;
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

    public void setFavoriteAuthors(List<BookAuthor> favoriteAuthors) {
        this.favoriteAuthors = favoriteAuthors;
    }

    public void setFavoriteCategories(List<BookCategory> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public void setBookOffers(List<Book> bookOffers) {
        this.bookOffers = bookOffers;
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