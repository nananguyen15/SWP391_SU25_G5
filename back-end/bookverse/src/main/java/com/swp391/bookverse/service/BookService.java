package com.swp391.bookverse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public Book addBook(Book b) {
        return bRepo.save(b);
    }

    public Book getBookById(long id) {
        return bRepo.findById(id).get();
    }

    public Book updatedBook(Book b) {
        return bRepo.save(b);
    }

    public void deleteById(long id) {
        bRepo.deleteById(id);
    }
}