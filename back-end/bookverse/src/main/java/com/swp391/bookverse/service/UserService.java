package com.swp391.bookverse.service;

import com.swp391.bookverse.dto.request.UserCreationRequest;
import com.swp391.bookverse.dto.request.UserUpdateRequest;
import com.swp391.bookverse.dto.response.UserResponse;
import com.swp391.bookverse.entity.User;
import com.swp391.bookverse.enums.Role;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.mapper.UserMapper;
import com.swp391.bookverse.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service // This annotation indicates that this class is a service component in the Spring context.
@RequiredArgsConstructor // Generates a constructor with required arguments for final fields.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // Sets the default access level for fields to private and makes them final.
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    /**
     * Creates a new user in the system.
     * @param request the request object containing user details
     * @return user the created user entity
     */
    public User createUser(UserCreationRequest request) {

        // make sure the username of request object is not already taken. Stop the process if it is.
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        // Create a new User entity with encoded password
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode((request.getPassword())));


        // Set default role USER
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.name());
        user.setRoles(roles);

        // Save the user to the repository and return the saved entity
        return userRepository.save(user);
    }

    /**
     * Fetches all users from the system.
     * @return List<User> a list of all users
     */
    public List<UserResponse> getUsers() {
        // throw exception if there are no user entity store in DB
        if (userRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_USERS_STORED);
        }

        // transfer list of User to list of UserResponse
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponses = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            usersResponses.add(userMapper.toUserResponse(users.get(i)));
        }

        // log retrieved users for debugging purposes
        System.out.println("Retrieved " + usersResponses.size() + " users from the repository.");
        for (UserResponse userResponse : usersResponses) {
            System.out.println("User: " + userResponse.getUsername() + ", Email: " + userResponse.getEmail());
        }
        // Fetch all users from the repository
        return usersResponses;
    }

    /**
     * Fetches a user by their ID.
     * @param id the ID of the user to fetch
     * @return User the user entity with the specified ID
     */
    public UserResponse getUserById(String id) {
        // Fetch a user by ID from the repository
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    /**
     * Updates an existing user in the system.
     * @param id the ID of the user to update
     * @param request the request object containing updated user details
     * @return user the updated user entity
     */
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(request, existingUser);

        // encoding password
        existingUser.setPassword(passwordEncoder.encode(existingUser.getPassword()));

        return userMapper.toUserResponse(userRepository.save(existingUser));
    }

    /**
     * Deletes a user from the system by their ID.
     * @param id  the ID of the user to delete
     */
    public void deleteUser(String id) {
        // Fetch the existing user by ID
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.delete(existingUser);
    }
}
