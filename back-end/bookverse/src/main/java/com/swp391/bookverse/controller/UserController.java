package com.swp391.bookverse.controller;

import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.dto.request.UserCreationRequest;
import com.swp391.bookverse.dto.request.UserUpdateRequest;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @PostMapping("/create")
    public APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        APIResponse<User> response = new APIResponse<>();

        response.setResult(userService.createUser(request));

       return response;
    }

    @GetMapping("/get-all")
    public APIResponse<List<UserResponse>> getUsers(){
        System.out.println("Received request to get all users");
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setResult(userService.getUsers());
        return response;
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        System.out.println("Received request to get user by ID: " + userId);
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UserUpdateRequest request) {
        System.out.println("Received request to update user with ID: " + userId);
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        System.out.println("Received request to delete user with ID: " + userId);
        userService.deleteUser(userId);
        return "User with ID " + userId + " deleted successfully.";
    }
}
