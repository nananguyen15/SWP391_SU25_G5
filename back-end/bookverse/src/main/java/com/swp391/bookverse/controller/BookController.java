package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.response.BookResponse;
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

    /**
     * 1.Add Book - Create a new book
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/book
     * 
     * @param book
     * @return
     */
    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody Book book) {
        Book saveBook = service.saveBook(book);
        BookResponse resp = new BookResponse(saveBook);
        return ResponseEntity.ok(resp);
    }

    /**
     * 2.View book by ID (Xem sách theo ID)
     * Lấy thông tin chi tiết của một cuốn sách dựa trên ID.
     * Nếu sách không tồn tại, trả về mã trạng thái 404 Not Found.
     * Đường dẫn: /api/book/{id}
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id) {
        final Optional<BookResponse> BookById = service.getBookResponseById(id);

        if (BookById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(BookById.get());
    }

    /**
     * 4.Tìm kiếm sách (Dành cho Khách hàng)
     * - Chỉ tìm kiếm trên các sách đang bán/hiển thị (có tồn kho).
     * - Chỉ trả về các trường thông tin công khai (không bao gồm thông tin nội bộ
     * như chi phí nhập, nhà cung cấp).
     * - Không yêu cầu xác thực (Authorization).
     * Đường dẫn: /api/book/search?text=keyword&page=0&size=10
     * 
     * @param text
     * @param pageable
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> searchBooksForCustomer(
            @RequestParam String text,
            @PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<BookResponse> booksPage = service.searchBookResponsesForCustomer(text, pageable);

        return ResponseEntity.ok(booksPage);
    }

    /**
     * Tìm kiếm sách (Dành cho Admin/Staff)
     * - Cho phép tìm kiếm trên tất cả sách (kể cả sách bị ẩn, hết hàng).
     * - Có thể tìm kiếm trên các trường phụ trợ (ví dụ: supplier code, warehouse
     * location).
     * - Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/book/admin-search?text=keyword&page=0&size=20
     * 
     * @param text
     * @param pageable
     * @return
     */
    @GetMapping("/admin-search")
    public ResponseEntity<Page<BookResponse>> searchBooksForAdmin(
            @RequestParam String text,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BookResponse> booksPage = service.searchBookResponsesForAdmin(text, pageable);

        return ResponseEntity.ok(booksPage);
    }

    /**
     * 6.Filter book (Lọc sách theo danh mục con) (subCategoryId)
     * GET /api/book/filter?subCategoryId=1
     * 
     * @param subCategoryId
     * @return
     */
    @GetMapping("/filter")
    public ResponseEntity<List<BookResponse>> filterBooksBySubCategory(@RequestParam Long subCategoryId) {
        List<BookResponse> books = service.filterBookResponsesBySubCategory(subCategoryId);
        return ResponseEntity.ok(books);
    }

    /**
     * 7.Update book
     * PUT /api/book/{id}
     * Partial update: only provided fields in request body will be updated
     * 
     * @param id   path id
     * @param book fields to update
     * @return updated BookResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatedBook(@PathVariable("id") Long id, @RequestBody Book book) {
        // allow partial update: only provided fields in request body will be updated
        book.setId(id);

        Book updatedBook = service.updateBook(book);
        BookResponse resp = new BookResponse(updatedBook);
        return ResponseEntity.ok(resp);
    }

    /**
     * 8.Delete book by ID
     * DELETE /api/book/{id}
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        service.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 9. Get all books (Lấy tất cả sách)
     * GET /api/book/all
     * 
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = service.getAllBookResponses();
        return ResponseEntity.ok(books);
    }

}