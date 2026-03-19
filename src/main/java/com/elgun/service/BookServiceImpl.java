package com.elgun.service;

import com.elgun.dao.entity.Book;
import com.elgun.dao.repository.BookRepository;
import com.elgun.Dto.BookRequestDto;
import com.elgun.Dto.BookResponseDto;
import com.elgun.Dto.BookUpdateDto;
import com.elgun.enumm.BookAvailability;
import com.elgun.fetcher.EntityFetch;
import com.elgun.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final EntityFetch entityFetch;
    @Override
    public void addBook(BookRequestDto bookRequestDto) {
       bookRepository.save(bookMapper.mapRequestDtoToEntity(bookRequestDto));
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        return bookMapper.mapEntityToResponseDto(entityFetch.fetchBookIfExists(id));
    }

    @Override
    public Page<BookResponseDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bookRepository.findAll(pageable).map(bookMapper::mapEntityToResponseDto);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = entityFetch.fetchBookIfExists(id);
        book.setBookAvailability(BookAvailability.UNAVAILABLE);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookUpdateDto bookUpdateDto) {
        Book book = entityFetch.fetchBookIfExists(id);
        Optional.ofNullable(bookUpdateDto.getBookName()).ifPresent(book::setBookName);
        Optional.ofNullable(bookUpdateDto.getPrice()).ifPresent(book::setPrice);
        Optional.ofNullable(bookUpdateDto.getBookCount()).ifPresent(book::setBookCount);
        Optional.ofNullable(bookUpdateDto.getAuthorName()).ifPresent(book::setAuthorName);
        Optional.ofNullable(bookUpdateDto.getPublishDate()).ifPresent(book::setPublishDate);
        bookRepository.save(book);
    }

}
