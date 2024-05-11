package group.BookstoreApplication.formsdata;

public class SearchDTO {
    private String title;
    private String authors;
    private String searchStrategy;


    public SearchDTO() {

    }

    public SearchDTO(String authors, String searchStrategy) {
        this.authors = authors;
        this.searchStrategy = searchStrategy;
    }

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
