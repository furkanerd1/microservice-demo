package com.kitaplik.book_service.repository;

import com.kitaplik.book_service.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIsbn(String isbn);
}
