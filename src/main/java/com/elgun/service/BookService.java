package com.elgun.service;

import com.elgun.Dto.BookRequestDto;
import com.elgun.Dto.BookResponseDto;

public interface BookService {
    public void createBook(BookRequestDto bookRequestDto);
    public BookResponseDto findBookById(Long id);
}
