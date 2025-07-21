package com.kitaplik.book_service;

import com.kitaplik.book_service.model.entity.Book;
import com.kitaplik.book_service.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Book> books = List.of(
				Book.builder()
						.title("Sefiller")
						.bookYear(1862)
						.author("Victor Hugo")
						.pressName("Penguin Classics")
						.isbn("123456")
						.build(),

				Book.builder()
						.title("Suç ve Ceza")
						.bookYear(1866)
						.author("Fyodor Dostoyevski")
						.pressName("Can Yayınları")
						.isbn("456789")
						.build(),

				Book.builder()
						.title("Kürk Mantolu Madonna")
						.bookYear(1943)
						.author("Sabahattin Ali")
						.pressName("Yapı Kredi Yayınları")
						.isbn("789456")
						.build()
		);
		bookRepository.saveAll(books);

	}
}
