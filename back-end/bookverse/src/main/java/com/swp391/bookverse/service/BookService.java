package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.BookCreationRequest;
import com.swp391.bookverse.dto.request.BookUpdateRequest;
import com.swp391.bookverse.dto.response.BookResponse;
import com.swp391.bookverse.entity.Author;
import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.entity.Publisher;
import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.repository.AuthorRepository;
import com.swp391.bookverse.repository.BookRepository;
import com.swp391.bookverse.repository.PublisherRepository;
import com.swp391.bookverse.repository.SubCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;
    SubCategoryRepository subCategoryRepository;
    BookRepository bookRepository;

    /**
     * Create a new book based on the provided request.
     * @param request
     * @return APIResponse containing the created book
     */
    public APIResponse<Book> createBook(BookCreationRequest request) {
        if (bookRepository.existsByTitle(request.getTitle())) {
            throw new AppException(ErrorCode.BOOK_EXISTS);
        }

        APIResponse<Book> response = new APIResponse<>();
        Book book = mapToBookEntity(request);

        bookRepository.save(book);
        response.setResult(book);
        return response;
    }

    /**
     * Get all books stored in the system.
     * @return APIResponse containing a list of BookResponse objects
     */
    public APIResponse<List<BookResponse>> getBooks() {
        if (bookRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_BOOKS_STORED);
        }

        APIResponse<List<BookResponse>> response = new APIResponse<>();
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>(books.size());
        for (Book book : books) {
            BookResponse bookResponse = mapToBookResponse(book);
            bookResponses.add(bookResponse);
        }

        response.setResult(bookResponses);
        return response;
    }

    /**
     * Get a book by its ID.
     * @param bookId
     * @return BookResponse containing the book details
     */
    public BookResponse getBookById(String bookId) {
        Book book = bookRepository.findById(Long.parseLong(bookId))
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
        return mapToBookResponse(book);
    }

    /**
     * Update an existing book's details.
     * @param bookId
     * @param request
     * @return BookResponse containing the updated book details
     */
    public BookResponse updateBook(Long bookId, BookUpdateRequest request) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        // Update fields
        existingBook.setTitle(request.getTitle());
        existingBook.setDescription(request.getDescription());
        existingBook.setPrice(request.getPrice());
        existingBook.setStockQuantity(request.getStockQuantity());
        existingBook.setPublishedDate(request.getPublishedDate());
        existingBook.setImage(request.getImage());
        existingBook.setActive(request.getActive());

        // Save updated book
        Book updatedBook = bookRepository.save(existingBook);
        return mapToBookResponse(updatedBook);
    }

    /**
     * Map the BookCreationRequest to a Book entity.
     * @param request the book creation request
     * @return the mapped Book entity
     */
    private Book mapToBookEntity(BookCreationRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new AppException(ErrorCode.PUBLISHER_NOT_FOUND));

        SubCategory category = subCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        return Book.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .author(author)
                .publisher(publisher)
                .category(category)
                .stockQuantity(request.getStockQuantity())
                .publishedDate(request.getPublishedDate())
                .image(request.getImage())
                .active(request.getActive())
                .build();
    }

    private BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .price(book.getPrice())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .categoryId(book.getCategory().getId())
                .stockQuantity(book.getStockQuantity())
                .publishedDate(book.getPublishedDate())
                .image(book.getImage())
                .active(book.getActive())
                .build();
    }
}
