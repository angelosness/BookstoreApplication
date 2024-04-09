package group.BookstoreApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        //for auto increment
    @Column(name="id")
    private int id;

    @Column(name="username", unique=true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="age")
    private int age;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public void setLast_name(String LastName) {
        this.last_name = LastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() { return first_name; }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }
}