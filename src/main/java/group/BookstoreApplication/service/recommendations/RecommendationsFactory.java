package group.BookstoreApplication.service.recommendations;


public class RecommendationsFactory {

    public static RecommendationsStrategy create(String strategy){
        if(strategy.equals("Author")){
            return new RecommendationsAuthors();
        }
        else if(strategy.equals("Categories")){
            return new RecommendationsCategories();
        }
        else {  // if (strategy.equals("AuthorAndCategories")
            return new RecommendationsAuthorsAndCategories();
        }
    }

}