package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.response.CategoryResponse;
import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author nhung
 */

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository categoryRepository;

    // Lấy tất cả Category (entity)
    @Transactional(readOnly = true)
    public List<SubCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy tất cả Category (DTO)
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategoryResponses() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    // Lấy Category theo ID (entity)
    @Transactional(readOnly = true)
    public Optional<SubCategory> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Lấy Category theo ID (DTO)
    @Transactional(readOnly = true)
    public Optional<CategoryResponse> getCategoryResponseById(Long id) {
        return categoryRepository.findById(id).map(CategoryResponse::from);
    }

    // Thêm mới hoặc Cập nhật (nếu ID tồn tại) - trả entity
    @Transactional
    public SubCategory saveCategory(SubCategory category) {
        return categoryRepository.save(category);
    }

    // Thêm mới hoặc Cập nhật (trả DTO)
    @Transactional
    public CategoryResponse saveCategoryResponse(SubCategory category) {
        SubCategory saved = categoryRepository.save(category);
        return CategoryResponse.from(saved);
    }

    // Xóa Category
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Cập nhật có kiểm tra và xử lý lỗi (entity)
    @Transactional
    public SubCategory updateCategory(SubCategory category) {
        SubCategory existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + category.getId()));
        if (category.getName() != null)
            existingCategory.setName(category.getName());
        if (category.getDescription() != null)
            existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }

    // Cập nhật và trả DTO
    @Transactional
    public CategoryResponse updateCategoryResponse(SubCategory category) {
        SubCategory updated = updateCategory(category);
        return CategoryResponse.from(updated);
    }

    @Transactional(readOnly = true)
    public Optional<SubCategory> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryResponse> getCategoryResponseByName(String name) {
        return categoryRepository.findByName(name).map(CategoryResponse::from);
    }

    // Tìm các category có tên chứa text (case-insensitive) và trả DTO
    @Transactional(readOnly = true)
    public List<CategoryResponse> searchCategoryResponses(String text) {
        return categoryRepository.findByNameContainingIgnoreCase(text == null ? "" : text)
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }
}