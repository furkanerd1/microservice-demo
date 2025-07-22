package com.kitaplik.library_service.client;

import com.kitaplik.library_service.model.dto.BookDto;
import com.kitaplik.library_service.model.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "book-service",path = "/v1/book")
public interface BookServiceClient {

    Logger LOGGER = LoggerFactory.getLogger(BookServiceClient.class);
    @GetMapping
     ResponseEntity<List<BookDto>> getAllBook();


    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker",fallbackMethod = "getBookFallBack")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable("isbn")  String isbn);

    default ResponseEntity<BookIdDto> getBookFallBack(@PathVariable("isbn") String isbn,Exception exception) {
        LOGGER.info("Book not found by isbn + "+ isbn + "returning default BookIdDto object");
        return ResponseEntity.ok(new BookIdDto(UUID.randomUUID(),"default-isbn"));
    }


    @GetMapping("/book/{id}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getBookById(@PathVariable("id")  UUID id);

    default ResponseEntity<BookDto> getBookByIdFallback(UUID id, Exception exception) {
        LOGGER.info("Book not found by id " + id + ", returning default BookDto object");
        return ResponseEntity.ok(new BookDto(UUID.randomUUID(), "",0,"",""));
    }

}
