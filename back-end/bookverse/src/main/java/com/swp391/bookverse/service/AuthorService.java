package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.request.AuthorCreationRequest;
import com.swp391.bookverse.dto.response.AuthorResponse;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.Author;
import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.mapper.AuthorMapper;
import com.swp391.bookverse.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huangdat
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    /**
     * Create a new author based on the provided request
     * @param request the author creation request containing author details
     * @return the created Author entity
     */
    public Author createAuthor(AuthorCreationRequest request) {
        // make sure the name of author in request object is not already taken. Stop the process if it is.
        if(authorRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.AUTHOR_EXISTS);
        }
        // Create a new Author entity
        Author author = authorMapper.toAuthor(request);
        // Save the author to the repository and return the saved entity
        return authorRepository.save(author);
    }

    /**
     * Fetches all authors from the system.
     * @return List<AuthorResponse> a list of all authors
     */
    public List<AuthorResponse> getAuthors() {
        // throw exception if there are no author entity store in DB
        if (authorRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_AUTHORS_STORED);
        }

        // transfer list of Author to list of AuthorResponse
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> authorsResponses = new ArrayList<>(authors.size());
        for (int i = 0; i < authors.size(); i++) {
            authorsResponses.add(authorMapper.toAuthorResponse(authors.get(i)));
        }
        // Fetch all users from the repository
        return authorsResponses;
    }
}
