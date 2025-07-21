package com.kitaplik.library_service.service;

import com.kitaplik.library_service.client.BookServiceClient;
import com.kitaplik.library_service.exception.LibraryNotFoundException;
import com.kitaplik.library_service.model.dto.AddBookRequest;
import com.kitaplik.library_service.model.dto.LibraryDto;
import com.kitaplik.library_service.model.entity.Library;
import com.kitaplik.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;


    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibrary(UUID id) {
        Library libray = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id "+id));

        return new LibraryDto(
                libray.getLibraryId(),
                libray.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList())
        );
    }

    public LibraryDto createLibrary(){
        Library library = libraryRepository.save(new Library());
        return  new LibraryDto(library.getLibraryId(),null);
    }

    public void addBookToLibrary(AddBookRequest request){
        UUID bookId = bookServiceClient.getBookByIsbn(request.isbn()).getBody().bookId();

        Library library= libraryRepository.findById(request.id()).orElseThrow(() -> new LibraryNotFoundException("Library could not found by id "+request.id()));

        library.getUserBook().add(bookId);

        libraryRepository.save(library);
    }
}

