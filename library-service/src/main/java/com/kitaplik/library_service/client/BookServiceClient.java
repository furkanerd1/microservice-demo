package com.kitaplik.library_service.client;

import com.kitaplik.library_service.model.dto.BookDto;
import com.kitaplik.library_service.model.dto.BookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "book-service",path = "/v1/book")
public interface BookServiceClient {

    @GetMapping
     ResponseEntity<List<BookDto>> getAllBook();


    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable("isbn")  String isbn);


    @GetMapping("/book/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable("id")  UUID id);

}
