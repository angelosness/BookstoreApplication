package group.BookstoreApplication;

import group.BookstoreApplication.controller.UserController;
import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;
import group.BookstoreApplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(UserController.class)
public class TestUserController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @WithMockUser("test")
    @Test
    void testShowOfferListReturnsPage() throws Exception {
        Book book = new Book();
        book.setBookCategory(new BookCategory());

        Mockito.when(userService.retrieveOfferList("test")).thenReturn(List.of(book));

        mockMvc.perform(get("/list"))
            .andExpect(status().isOk())
            .andExpect(view().name("user/list"));

        // check if username is passed successfully
        Mockito.verify(userService).retrieveOfferList("test");
    }

    @WithMockUser("test")
    @Test
    void testHomepageReturnsPage() throws Exception {
        User user = new User();

        Book book = new Book();
        book.setBookCategory(new BookCategory());

        Mockito.when(userService.retrieveProfile("test")).thenReturn(user);
        Mockito.when(userService.showRecommendations("Author", user)).thenReturn(List.of(book));

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("strategy", "Author");

        mockMvc.perform(get("/")
                .params(multiValueMap))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));

        // check if param is passed successfully
        Mockito.verify(userService).showRecommendations("Author", user);
    }

    @WithMockUser("test")
    @Test
    void testOfferBookReturnsPage() throws Exception {
        Mockito.when(userService.retrieveCategories()).thenReturn(List.of(new BookCategory()));

        mockMvc.perform(get("/offer"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer"));
    }

    @WithMockUser("test")
    @Test
    void testSearchReturnsPage() throws Exception {
        SearchDTO searchDTO = new SearchDTO();

        Book book = new Book();
        book.setBookCategory(new BookCategory());       // object fields in book should not be null
        book.setOfferingUser(new User());

        Mockito.when(userService.searchBooks(any())).thenReturn(List.of(book));
        Mockito.when(userService.retrieveProfile("test")).thenReturn(new User());

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("title", searchDTO.getTitle());
        multiValueMap.add("authors", searchDTO.getAuthors());
        multiValueMap.add("searchStrategy", searchDTO.getSearchStrategy());

        mockMvc.perform(post("/search")
                .with(csrf())                           // otherwise returns 403
                .params(multiValueMap))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));
    }

    @WithMockUser("test")
    @Test
    void testShowRequestListReturnsPage() throws Exception {
        Book book = new Book();
        book.setOfferingUser(new User());

        Mockito.when(userService.retrieveRequestList("test")).thenReturn(List.of(book));
        Mockito.when(userService.retrieveProfile("test")).thenReturn(new User());

        mockMvc.perform(get("/reqlist"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/requests"));
    }
}
