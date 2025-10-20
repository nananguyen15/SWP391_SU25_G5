package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.AuthorCreationRequest;
import com.swp391.bookverse.dto.response.AuthorResponse;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.Author;
import com.swp391.bookverse.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author huangdat
 */
@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorController {
    AuthorService authorService;

    @PostMapping("/create")
    public APIResponse<Author> createAuthor(@RequestBody @Valid AuthorCreationRequest request) {
        APIResponse<Author> response = new APIResponse<>();
        response.setResult(authorService.createAuthor(request));
         return response;
    }

    @GetMapping("/get-all")
    public APIResponse<List<AuthorResponse>> getAuthors(){
        APIResponse<List<AuthorResponse>> response = new APIResponse<>();
        response.setResult(authorService.getAuthors());
        return response;
    }
}
