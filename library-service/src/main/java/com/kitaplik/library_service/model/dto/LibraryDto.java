package com.kitaplik.library_service.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LibraryDto(
        UUID id,
        List<BookDto> userBookList
){
    public LibraryDto{
        if(userBookList == null ||  userBookList.isEmpty()){
            userBookList = new ArrayList<>();
        }
    }
}
