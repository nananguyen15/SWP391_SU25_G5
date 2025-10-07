package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author nhung
 */

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // 1. Thêm sách mới (Create book)
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    //2. Lấy sách theo ID (Sử dụng Optional)
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    //3. Lấy sách theo Title
    @Transactional(readOnly = true)
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // 4. Cập nhật chi tiết sách (Áp dụng logic nghiệp vụ)
    @Transactional
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + book.getId()));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setImage(book.getImage());
        existingBook.setPrice(book.getPrice());
        existingBook.setStockQuantity(book.getStockQuantity());
        existingBook.setDescription(book.getDescription());
        existingBook.setCategory(book.getCategory());

        return bookRepository.save(existingBook);
    }

    // 5. Xóa sách theo ID
    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    // 6. Lấy tất cả sách
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 7. Lọc sách theo SubCategory ID (Filter book)
    @Transactional(readOnly = true)
    public List<Book> filterBooksBySubCategory(Long subCategoryId) {
        return bookRepository.findByCategory_Id(subCategoryId);
    }

    // 8. Phương thức để xử lý phân trang
    @Transactional(readOnly = true)
    public Page<Book> getPaginatedBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // 9. Search books for customer with pagination
    @Transactional(readOnly = true)
    public Page<Book> searchBooksForCustomer(String text, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStockQuantityGreaterThan(
                text, text, 0, pageable);
    }

    // 10. Search books for admin with pagination (all books)
    @Transactional(readOnly = true)
    public Page<Book> searchBooksForAdmin(String text, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                text, text, pageable);
    }
}