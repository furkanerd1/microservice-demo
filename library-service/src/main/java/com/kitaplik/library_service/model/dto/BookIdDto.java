package com.kitaplik.library_service.model.dto;

import java.util.UUID;

public record BookIdDto(
        UUID bookId,
        String isbn
) {
}
