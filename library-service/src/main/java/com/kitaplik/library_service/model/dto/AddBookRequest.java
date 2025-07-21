package com.kitaplik.library_service.model.dto;

import java.util.UUID;

public record AddBookRequest(
        //libraryId
        UUID id,
        String isbn
) {
}
