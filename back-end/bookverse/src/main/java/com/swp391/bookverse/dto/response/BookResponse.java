package com.swp391.bookverse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    String id;
    String title;
    String author;
    String publisher;
    String description;
    String image;
    Double price;
    Integer quantity;
    String categoryName;
    Boolean active;

    public BookResponse(com.swp391.bookverse.entity.Book book) {
        this.id = book.getId() == null ? null : String.valueOf(book.getId());
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.image = book.getImage();
        this.price = book.getPrice();
        this.quantity = book.getStockQuantity();
        this.categoryName = book.getCategory() == null ? null : book.getCategory().getName();
        this.active = book.getActive();
        this.author = book.getAuthor() == null ? null : book.getAuthor().getName();
        this.publisher = book.getPublisher() == null ? null : book.getPublisher().getName();
    }
}
