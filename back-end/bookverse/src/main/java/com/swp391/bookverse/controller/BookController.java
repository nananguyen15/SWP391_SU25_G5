package com.swp391.bookverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp391.bookverse.dto.response.APIResponse
import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.service.BookService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService service;
    @Autowired
    public BookController(BookService bookService){
        this.service = bookService;
    }


    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book b) {
        Book saveBook = service.addBook(b);
        return ResponseEntity.ok(saveBook);
    }

    @GetMapping("/getBook/{titleBook}")
    public ResponseEntity<Book> getBookById(@PathVariable("titleBook") String title){
        final Book BookById= service.getBookById(b);
        return ResponseEntity.ok(BookById);
    }


    @PutMapping("/editBook")
    public ResponseEntity<Book> updatedBook(@RequestBody Book b) {
        Book updatedBook =service.getBookById(id);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")long id) {
        final Book BookById=  = service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}