package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.response.CategoryResponse;
import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author nhung
 */

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class CategoryController {

    private final SubCategoryService categoryService;

    /**
     * 1.Add Category - Create a new category
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/subcategories
     * 
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody SubCategory category) {
        CategoryResponse resp = categoryService.saveCategoryResponse(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    /**
     * 2.View all categories
     * Lấy tất cả các danh mục (categories).
     * Đường dẫn: /api/subcategories
     * 
     * @return
     */
    @GetMapping("/viewAll")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> list = categoryService.getAllCategoryResponses();
        return ResponseEntity.ok(list);
    }

    /**
     * 3.View category by ID
     * Lấy danh mục theo ID.
     * Nếu danh mục không tồn tại, trả về mã trạng thái 404 Not Found.
     * Đường dẫn: /api/subcategories/{id}
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryResponseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 4.View category by Name
     * Lấy danh mục theo tên.
     * Nếu danh mục không tồn tại, trả về mã trạng thái 404 Not Found.
     * Đường dẫn: /api/subcategories/name/{name}
     * 
     * @param text
     * @return
     */
    @GetMapping("/search/{text}")
    public ResponseEntity<List<CategoryResponse>> searchCategories(@PathVariable("text") String text) {
        List<CategoryResponse> results = categoryService.searchCategoryResponses(text);
        return ResponseEntity.ok(results);
    }

    /**
     * 5.Update category
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/subcategories
     * 
     * @param category
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody SubCategory category) {
        // set id từ path -> service sẽ chỉ cập nhật các trường không null
        category.setId(id);
        try {
            CategoryResponse resp = categoryService.updateCategoryResponse(category);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 6.Delete category by ID
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/subcategories/{id}
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}