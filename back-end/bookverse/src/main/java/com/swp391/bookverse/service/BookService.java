package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.response.BookResponse;
import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.repository.BookRepository;
import com.swp391.bookverse.repository.AuthorRepository;
import com.swp391.bookverse.repository.PublisherRepository;
import com.swp391.bookverse.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author nhung
 */

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    // 1. Thêm sách mới (Create book)
    @Transactional
    public Book saveBook(Book book) {
        // If incoming book contains only nested objects with id, load full entities
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElse(null));
        }
        if (book.getPublisher() != null && book.getPublisher().getId() != null) {
            book.setPublisher(publisherRepository.findById(book.getPublisher().getId()).orElse(null));
        }
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            book.setCategory(subCategoryRepository.findById(book.getCategory().getId()).orElse(null));
        }

        return bookRepository.save(book);
    }

    // 2. Lấy sách theo ID (Sử dụng Optional)
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<BookResponse> getBookResponseById(Long id) {
        return bookRepository.findById(id).map(BookResponse::new);
    }

    // 3. Lấy sách theo Title
    @Transactional(readOnly = true)
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public Optional<BookResponse> getBookResponseByTitle(String title) {
        Book b = bookRepository.findByTitle(title);
        return b == null ? Optional.empty() : Optional.of(new BookResponse(b));
    }

    // 4. Cập nhật chi tiết sách (Áp dụng logic nghiệp vụ)
    @Transactional
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + book.getId()));
        // Partial update: only update fields that are non-null in incoming book
        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }

        if (book.getAuthor() != null) {
            if (book.getAuthor().getId() != null) {
                existingBook.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElse(null));
            } else {
                // if client provided author object without id, try to set it directly
                existingBook.setAuthor(book.getAuthor());
            }
        }

        if (book.getImage() != null) {
            existingBook.setImage(book.getImage());
        }

        if (book.getPrice() != null) {
            existingBook.setPrice(book.getPrice());
        }

        if (book.getStockQuantity() != null) {
            existingBook.setStockQuantity(book.getStockQuantity());
        }

        if (book.getDescription() != null) {
            existingBook.setDescription(book.getDescription());
        }

        if (book.getCategory() != null) {
            if (book.getCategory().getId() != null) {
                existingBook.setCategory(subCategoryRepository.findById(book.getCategory().getId()).orElse(null));
            } else {
                existingBook.setCategory(book.getCategory());
            }
        }

        if (book.getPublisher() != null) {
            if (book.getPublisher().getId() != null) {
                existingBook.setPublisher(publisherRepository.findById(book.getPublisher().getId()).orElse(null));
            } else {
                existingBook.setPublisher(book.getPublisher());
            }
        }

        if (book.getActive() != null) {
            existingBook.setActive(book.getActive());
        }

        if (book.getPublishedDate() != null) {
            existingBook.setPublishedDate(book.getPublishedDate());
        }

        return bookRepository.save(existingBook);
    }

    // 5. Xóa sách theo ID
    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    // 6. Lấy tất cả sách
    // In BookService.java
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBookResponses() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    // 7. Lọc sách theo SubCategory ID (Filter book)
    @Transactional(readOnly = true)
    public List<Book> filterBooksBySubCategory(Long subCategoryId) {
        return bookRepository.findByCategory_Id(subCategoryId);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> filterBookResponsesBySubCategory(Long subCategoryId) {
        return bookRepository.findByCategory_Id(subCategoryId)
                .stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    // 8. Phương thức để xử lý phân trang
    @Transactional(readOnly = true)
    public Page<Book> getPaginatedBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getPaginatedBookResponses(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookResponse::new);
    }

    // 9. Search books for customer with pagination
    @Transactional(readOnly = true)
    public Page<Book> searchBooksForCustomer(String text, Pageable pageable) {
        return bookRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStockQuantityGreaterThan(
                        text, text, 0, pageable);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> searchBookResponsesForCustomer(String text, Pageable pageable) {
        return bookRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStockQuantityGreaterThan(
                        text, text, 0, pageable)
                .map(BookResponse::new);
    }

    // 10. Search books for admin with pagination (all books)
    @Transactional(readOnly = true)
    public Page<Book> searchBooksForAdmin(String text, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                text, text, pageable);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> searchBookResponsesForAdmin(String text, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                text, text, pageable)
                .map(BookResponse::new);
    }
}