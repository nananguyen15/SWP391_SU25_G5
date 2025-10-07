package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.SubCategory;
import com.swp391.bookverse.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author nhung
 */


@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository categoryRepository;

    // Lấy tất cả Category
    @Transactional(readOnly = true)
    public List<SubCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy Category theo ID
    @Transactional(readOnly = true)
    public Optional<SubCategory> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Thêm mới hoặc Cập nhật (nếu ID tồn tại)
    @Transactional
    public SubCategory saveCategory(SubCategory category) {
        // Phương thức này có thể dùng cho cả INSERT và UPDATE nếu bạn không cần logic kiểm tra
        return categoryRepository.save(category);
    }

    // Xóa Category
    @Transactional
    public void deleteCategory(Long id) {
        // Tùy chọn: Thêm kiểm tra findById trước khi xóa để đưa ra Exception nếu ID không tồn tại
        categoryRepository.deleteById(id);
    }

    // --- Phương thức Cập nhật có kiểm tra và xử lý lỗi ---

    @Transactional
    public SubCategory updateCategory(SubCategory category) {
        // 1. Tìm kiếm Category hiện tại bằng ID.
        // Sử dụng orElseThrow để ném ra một ngoại lệ (Exception) nếu không tìm thấy.
        // Đây là cách chuẩn để xử lý lỗi "Not Found" trong Service Layer.
        SubCategory existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + category.getId()));
        // Trong thực tế nên dùng một Custom Exception (ví dụ: ResourceNotFoundException)

        // 2. Cập nhật các trường dữ liệu từ đối tượng mới
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        // Bổ sung các trường khác (ví dụ: SuperCategory, description) nếu cần cập nhật

        // 3. Lưu lại đối tượng đã cập nhật (JpaRepository sẽ thực hiện UPDATE)
        return categoryRepository.save(existingCategory);
    }

    @Transactional(readOnly = true)
    public Optional<SubCategory> getCategoryByName(String name) {
        // Gọi phương thức Repository đã khai báo ở trên
        return categoryRepository.findByName(name);
    }
}