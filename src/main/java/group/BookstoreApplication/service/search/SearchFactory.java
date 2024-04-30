package group.BookstoreApplication.service.search;

public class SearchFactory {

    public static SearchStrategy create(String strategy){
        if(strategy.equals("Approximate")){
            return new ApproximateSearchStrategy();
        }
        else{
            return new ExactSearchStrategy();
        }
    }
}
