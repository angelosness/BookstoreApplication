package group.BookstoreApplication.formsdata;

import java.util.ArrayList;
import java.util.List;

public class SearchDTO {
    private String title;
    private String authors;
    private String searchStrategy;

    //Setters and getters:
    public void setTitle (String title){
        this.title = title;
    }

    public void setAuthors (String authors)
    {
        this.authors = authors;
    }

    public void setSearchStrategy(String searchStrategy){
        this.searchStrategy = searchStrategy;
    }

    public String getTitle (){
        return this.title;
    }

    public String getAuthors()
    {
        return this.authors;
    }

    public String getSearchStrategy() {
        return searchStrategy;
    }
}
