package com.kitaplik.book_service.model.mapper;

import com.kitaplik.book_service.model.dto.BookDto;
import com.kitaplik.book_service.model.dto.BookIdDto;
import com.kitaplik.book_service.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toBook(BookDto bookDto);

    BookIdDto toBookIdDto(Book book);
}
