package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.Book;
import com.swp391.bookverse.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Author nhung
 */

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {


    private final BookService service;

    // POST /api/book
    // 1.Add Book - Create a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saveBook = service.saveBook(book);
        return ResponseEntity.ok(saveBook);
    }

    // GET /api/book/{id}
    // 2.View book by ID (Xem sách theo ID)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") Long id){
        final Optional<Book> BookById = service.getBookById(id);

        if (BookById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(BookById);
    }


    // GET /api/book/books
    // 3. View book list (Phân trang và Sắp xếp)
    // Đường dẫn: /api/book/books?page=0&size=10
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getPaginatedAndSortedBooks(
            // Đặt mặc định sắp xếp theo ngày xuất bản giảm dần
            @PageableDefault(size = 10, sort = "publicationDate", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Book> booksPage = service.getPaginatedBooks(pageable);
        return ResponseEntity.ok(booksPage);
    }

    // GET /api/books/search?text=tieuthuyet&page=0&size=10
// 4. Tìm kiếm Sách (Khách hàng)
    @GetMapping("/search")
    public ResponseEntity<Page<Book>> searchBooksForCustomer(
            @RequestParam String text,
            @PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {

        // Logic trong Service:
        // - Chỉ tìm kiếm sách đang bán/hiển thị.
        // - Chỉ trả về các trường thông tin công khai.
        Page<Book> booksPage = service.searchBooksForCustomer(text, pageable);

        return ResponseEntity.ok(booksPage);
    }

    // GET /api/books/admin-search?text=tieuthuyet&page=0&size=10
    // 5. Search (Admin/Staff)
    @GetMapping("/admin-search")
    public ResponseEntity<Page<Book>> searchBooksForAdmin(
            @RequestParam String text,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // Logic trong Service:
        // - Tìm kiếm toàn bộ sách (kể cả sách bị ẩn, hết hàng).
        // - Có thể tìm kiếm trên các trường phụ trợ (ví dụ: supplier code, warehouse location).
        // - Yêu cầu xác thực (Authorization) quyền Admin/Staff.
        Page<Book> booksPage = service.searchBooksForAdmin(text, pageable);

        return ResponseEntity.ok(booksPage);
    }

    // GET /api/book/filter?subCategoryId=1
    // 6.Filter book (Lọc sách theo danh mục con)
    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooksBySubCategory(@RequestParam Long subCategoryId) {
        List<Book> books = service.filterBooksBySubCategory(subCategoryId);
        return ResponseEntity.ok(books);
    }

    // PUT /api/book
    // 7.Update book
    @PutMapping
    public ResponseEntity<Book> updatedBook(@RequestBody Book book) {
        Book updatedBook = service.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    // DELETE /api/book/{id}
    // 8.Delete book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        service.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    // GET /api/book/all
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

}
