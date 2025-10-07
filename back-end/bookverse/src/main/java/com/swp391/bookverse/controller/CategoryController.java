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

    // POST /api/subcategories
    // 1.Add Category - Create a new category
    @PostMapping
    public ResponseEntity<SubCategory> addCategory(@RequestBody SubCategory category) {
        SubCategory savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // GET /api/subcategories
    // 2.View all categories
    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllCategories() {
        List<SubCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // GET /api/subcategories/{id}
    // 3.View category by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /api/subcategories/name/{name}
    // 4.View category by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<SubCategory> getCategoryByName(@PathVariable String name) {
        Optional<SubCategory> categoryOpt = categoryService.getCategoryByName(name);

        return categoryOpt
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT /api/subcategories
    // 5.Update category
    @PutMapping
    public ResponseEntity<SubCategory> updateCategory(@RequestBody SubCategory category) {
        try {
            SubCategory updatedCategory = categoryService.updateCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/subcategories/{id}
    // 6.Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
