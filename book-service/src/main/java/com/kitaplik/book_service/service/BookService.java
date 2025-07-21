package com.kitaplik.book_service.service;

import com.kitaplik.book_service.exception.BookNotFoundException;
import com.kitaplik.book_service.model.dto.BookDto;
import com.kitaplik.book_service.model.dto.BookIdDto;
import com.kitaplik.book_service.model.mapper.BookMapper;
import com.kitaplik.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toBookIdDto)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: "+ isbn));
    }

    public BookDto findBookDetailsById(UUID id){
        return bookRepository.findById(id)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: "+ id));
    }
}
