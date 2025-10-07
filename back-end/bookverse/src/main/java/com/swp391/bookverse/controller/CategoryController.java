package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<SubCategory> addCategory(@RequestBody SubCategory category) {
        SubCategory savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }


    /**
     * 2.View all categories
     * Lấy tất cả các danh mục (categories).
     * Đường dẫn: /api/subcategories
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllCategories() {
        List<SubCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    /**
     * 3.View category by ID
     * Lấy danh mục theo ID.
     * Nếu danh mục không tồn tại, trả về mã trạng thái 404 Not Found.
     * Đường dẫn: /api/subcategories/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 4.View category by Name
     * Lấy danh mục theo tên.
     * Nếu danh mục không tồn tại, trả về mã trạng thái 404 Not Found.
     * Đường dẫn: /api/subcategories/name/{name}
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<SubCategory> getCategoryByName(@PathVariable String name) {
        Optional<SubCategory> categoryOpt = categoryService.getCategoryByName(name);

        return categoryOpt
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 5.Update category
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/subcategories
     * @param category
     * @return
     */
    @PutMapping
    public ResponseEntity<SubCategory> updateCategory(@RequestBody SubCategory category) {
        try {
            SubCategory updatedCategory = categoryService.updateCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * 6.Delete category by ID
     * Yêu cầu xác thực (Authorization) quyền Admin/Staff.
     * Đường dẫn: /api/subcategories/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}