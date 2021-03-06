package samples.springboot.gs.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by izeye on 14. 12. 23..
 */
@Repository
public class SimpleBookRepository implements BookRepository {

	@Override
	@Cacheable("books")
	public Book getByIsbn(String isbn) {
//		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	@Override
//	@CacheEvict(value = "books", key = "#book.isbn")
	@CacheEvict(value = "books", key = "#book.isbn == null ? '' : #book.isbn")
	public void save(Book book) {
		// Do nothing.
	}

	private void simulateSlowService() {
		try {
			long time = 5000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
