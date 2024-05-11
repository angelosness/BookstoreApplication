package group.BookstoreApplication;

import group.BookstoreApplication.config.WebSecurityConfig;
import group.BookstoreApplication.dao.BookAuthorDAO;
import group.BookstoreApplication.dao.BookCategoryDAO;
import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.dao.UserDAO;
import group.BookstoreApplication.formsdata.ProfileDTO;
import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;
import group.BookstoreApplication.service.UserService;
import group.BookstoreApplication.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(SpringExtension.class)
class TestUserService {
	@TestConfiguration
	static class UserServiceTestContextConfiguration extends WebSecurityConfig {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	UserService userService;

	@MockBean
	UserDAO userDAO;

	@MockBean
	BookDAO bookDAO;

	@MockBean
	BookAuthorDAO bookAuthorDAO;

	@MockBean
	BookCategoryDAO bookCategoryDAO;


	@Test
	void testUserServiceIsNotNull() {
		Assertions.assertNotNull(userService);
	}

	@Test
	void testAddOfferSavesBook() {
		User user = new User();

		List<BookAuthor> authorList = new ArrayList<BookAuthor>();
		BookAuthor author = new BookAuthor("Bob");						// single author case
		authorList.add(author);

		BookCategory category = new BookCategory("Fantasy");

		Book book = new Book("Bob in Wonderland", authorList, category, "test");

		Mockito.when(bookAuthorDAO.findByName("Bob")).thenReturn(author);		// author already in db case
		Mockito.when(bookCategoryDAO.findByName("Fantasy")).thenReturn(category);
		Mockito.when(userDAO.findByUsername(any())).thenReturn(user);

		userService.addOffer("", book);

		Assertions.assertEquals(book, user.getBookOffers().get(0));				// compare test book to book in user's offer list
		Mockito.verify(userDAO).save(user);										// if verified, book is saved due to cascade
	}

	@Test
	void testUpdateUserSavesChanges() {
		User user = new User();

		List<String> favCategories = new ArrayList<String>();
		favCategories.add("Art");												// one favorite category case
		BookCategory category = new BookCategory("Art");

		ProfileDTO form = new ProfileDTO(user, favCategories);
		Mockito.when(bookCategoryDAO.findByName("Art")).thenReturn(category);

		userService.updateUser(form);

		Assertions.assertEquals(category, user.getFavoriteCategories().get(0));	// category only worth checking
		Mockito.verify(userDAO).save(user);
	}

	@Test
	void testOfferBookToUserChangesBook() {
		User user = new User();

		Book book = new Book();

		Mockito.when(bookDAO.findById(0)).thenReturn(book);
		Mockito.when(userDAO.findById(0)).thenReturn(user);

		userService.offerBookToUser(0,0);

		Assertions.assertEquals("UNAVAILABLE", book.getStatus());
		Assertions.assertEquals(user, book.getAcceptedUser());
		Mockito.verify(bookDAO).save(book);
	}

	@Test
	void testRequestBookAddsUserToRequestList() {
		User user = new User();

		Book book = new Book();

		Mockito.when(userDAO.findByUsername(any())).thenReturn(user);
		Mockito.when(bookDAO.findById(0)).thenReturn(book);

		userService.requestBook("", 0);

		// check if user is in the requesting users list of the test book
		Assertions.assertEquals(user, book.getRequestingUsers().get(0));
	}

	@Test
	void testSearchBooksApproximateReturnsDesiredResult() {
		Book book = delegatedFreshSetup();

		// approximate test search - one of the two authors
		SearchDTO form = new SearchDTO("Kami Garcia", "Approximate");

		Mockito.when(bookDAO.findByTitleContaining(any())).thenReturn(List.of(book));

		List<Book> result = userService.searchBooks(form);

		Assertions.assertEquals(List.of(book), result);
		Mockito.verify(bookDAO).findByTitleContaining(any());
	}

	@Test
	void testSearchBooksExactReturnsDesiredResult() {
		Book book = delegatedFreshSetup();

		// exact test search - all authors
		SearchDTO form = new SearchDTO("Kami Garcia, Margaret Stohl", "Exact");

		Mockito.when(bookDAO.findByTitle(any())).thenReturn(List.of(book));

		List<Book> result = userService.searchBooks(form);

		Assertions.assertEquals(List.of(book), result);
		Mockito.verify(bookDAO).findByTitle(any());
	}

	@Test
	void testSearchBooksExactNotMatchingAllAuthors() {
		Book book = delegatedFreshSetup();
		SearchDTO form = new SearchDTO("Kami Garcia", "Exact");

		Mockito.when(bookDAO.findByTitle(any())).thenReturn(List.of(book));

		List<Book> result = userService.searchBooks(form);

		Assertions.assertTrue(result.isEmpty());		// authors don't match fully
	}

	@Test
	void testShowRecommendationsCategoriesReturnsDesiredResult() {
		User user = new User(1);
		User notThisUser = new User(2);

		Book book1 = new Book();
		book1.setOfferingUser(user);
		Book book2 = new Book();
		book2.setOfferingUser(notThisUser);
		book2.setRequestingUsers(List.of(user));
		Book book3 = new Book();
		book3.setOfferingUser(notThisUser);

		Mockito.when(bookDAO.findByStatusAndBookCategoryIn(eq("AVAILABLE"), any())).thenReturn(List.of(book1, book2, book3));

		List<Book> result = userService.showRecommendations("Categories", user);

		// this test focuses on checking if the returned books are neither offered nor requested by the user
		Assertions.assertEquals(List.of(book3), result);
		Mockito.verify(bookDAO).findByStatusAndBookCategoryIn(eq("AVAILABLE"), any());
	}

	private Book delegatedFreshSetup() {
		List<BookAuthor> authorList = new ArrayList<BookAuthor>();
		authorList.add(new BookAuthor("Kami Garcia"));
		authorList.add(new BookAuthor("Margaret Stohl"));

		return new Book("Beautiful Creatures", authorList);
	}
}
