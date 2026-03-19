package com.elgun.service;

import com.elgun.Dto.BookRequestDto;
import com.elgun.Dto.BookResponseDto;
import com.elgun.Dto.BookUpdateDto;
import org.springframework.data.domain.Page;

public interface BookService {
    void addBook(BookRequestDto bookRequestDto);
    BookResponseDto getBookById(Long id);
    Page<BookResponseDto> getAllBooks(int page, int size);
    void deleteBook(Long id);
    void updateBook(Long id, BookUpdateDto bookUpdateDto);
}
