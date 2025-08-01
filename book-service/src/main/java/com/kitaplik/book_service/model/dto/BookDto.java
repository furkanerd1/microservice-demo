package com.kitaplik.book_service.model.dto;

import java.util.UUID;

public record BookDto(
         UUID id,
         String title,
         Integer bookYear,
         String author,
         String pressName,
         String isbn
){}
