package com.kitaplik.book_service.service;

import com.kitaplik.book_service.exception.BookNotFoundException;
import com.kitaplik.book_service.model.dto.BookIdDto;
import com.kitaplik.book_service.repository.BookRepository;
import com.kitaplik.bookservice.dto.BookId;
import com.kitaplik.bookservice.dto.BookServiceGrpc;
import com.kitaplik.bookservice.dto.Isbn;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BookGrpcServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(BookGrpcServiceImpl.class);
    private final BookRepository bookRepository;

    public BookGrpcServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getBookIdByIsbn(Isbn isbn, StreamObserver<BookId> responseObserver) {
        logger.info("grpc call received "+ isbn.getIsbn());
        BookIdDto bookId = bookRepository.findByIsbn(isbn.getIsbn())
                .map(book ->new BookIdDto(book.getId(),book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn" + isbn.getIsbn()));
        responseObserver.onNext(
                BookId.newBuilder().setBookId(String.valueOf(bookId.bookId()))
                        .setIsbn(bookId.isbn())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
