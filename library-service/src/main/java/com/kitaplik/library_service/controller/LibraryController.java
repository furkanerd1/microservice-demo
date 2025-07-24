package com.kitaplik.library_service.controller;

import com.kitaplik.library_service.model.dto.AddBookRequest;
import com.kitaplik.library_service.model.dto.LibraryDto;
import com.kitaplik.library_service.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final Environment environment;

    Logger logger = LoggerFactory.getLogger(LibraryController.class);

    public LibraryController(LibraryService libraryService, Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }

    @GetMapping("{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable UUID id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibrary(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UUID>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }

}
