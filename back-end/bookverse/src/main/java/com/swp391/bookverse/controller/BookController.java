package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.BookCreationRequest;
import com.swp391.bookverse.dto.request.BookUpdateRequest;
import com.swp391.bookverse.dto.response.BookResponse;
import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookController {
    BookService bookService;

    @PostMapping("/create")
    public APIResponse<Book> createBook(@RequestBody @Valid BookCreationRequest request) {
        APIResponse<Book> response;
        response = bookService.createBook(request);
        return response;
    }

    @GetMapping("/get-all")
    public APIResponse<List<BookResponse>> getBooks(){
        APIResponse<List<BookResponse>> response;
        response = bookService.getBooks();
        return response;
    }

    @GetMapping("/{bookId}")
    public BookResponse getbook(@PathVariable("bookId") String bookId) {
        return bookService.getBookById(bookId);
    }

    @PutMapping("/update/{bookId}")
    public BookResponse updateBook(@PathVariable("bookId") Long bookId, @RequestBody @Valid BookUpdateRequest request) {
        return bookService.updateBook(bookId, request);
    }


}
