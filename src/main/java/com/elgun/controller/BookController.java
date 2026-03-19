package com.elgun.controller;

import com.elgun.Dto.BookRequestDto;
import com.elgun.Dto.BookResponseDto;
import com.elgun.Dto.BookUpdateDto;
import com.elgun.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Validated
@Tag(name = "Books", description = "Book management endpoints")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create book")
    public void addBook(@RequestBody @Valid BookRequestDto bookRequestDto){
        bookService.addBook(bookRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get book by ID")
    public BookResponseDto getBookById(@PathVariable @Min(1) Long id){
        return bookService.getBookById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books by page and size")
    public Page<BookResponseDto> getAllBooks(@RequestParam(defaultValue = "0") @Min(0) int page,@RequestParam(defaultValue = "10") @Min(1) int size){
        return bookService.getAllBooks(page,size);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book")
    public void deleteBook(@PathVariable @Min(1) Long id){
        bookService.deleteBook(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update book")
    public void updateBook(@PathVariable @Min(1) Long id, @RequestBody @Valid BookUpdateDto bookUpdateDto){
        bookService.updateBook(id,bookUpdateDto);
    }

}
