package group.BookstoreApplication.formsdata;

import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.ArrayList;
import java.util.List;

// for choosing multiple categories as favorite
public class ProfileDTO {
    private User user;
    private List<String> categories;

    public ProfileDTO(User user) {
        this.user = user;
        categories = new ArrayList<String>();

        List<BookCategory> categoryList = user.getFavoriteCategories();
        for (BookCategory bookCategory : categoryList) {
            categories.add(bookCategory.getName());
        }
    }

    public ProfileDTO(User user, List<String> categories) {
        this.user = user;
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }
    public List<String> getCategories() {
        return categories;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
