package com.elgun.mapper;
import com.elgun.dao.entity.Book;
import com.elgun.Dto.BookRequestDto;
import com.elgun.Dto.BookResponseDto;
import com.elgun.enumm.BookAvailability;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book mapRequestDtoToEntity(BookRequestDto bookRequestDto){
        return Book.builder().bookName(bookRequestDto.getBookName())
                .price(bookRequestDto.getPrice())
                .authorName(bookRequestDto.getAuthorName())
                .publishDate(bookRequestDto.getPublishDate())
                .bookCount(bookRequestDto.getBookCount())
                .bookAvailability(BookAvailability.AVAILABLE)
                .build();

    }
    public BookResponseDto mapEntityToResponseDto(Book book){
        return BookResponseDto.builder()
                .bookName(book.getBookName())
                .price(book.getPrice())
                .bookAvailability(book.getBookAvailability())
                .authorName(book.getAuthorName())
                .publishDate(book.getPublishDate())
                .build();
    }

}
