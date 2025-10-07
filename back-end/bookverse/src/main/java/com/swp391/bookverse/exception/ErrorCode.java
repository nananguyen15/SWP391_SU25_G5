package com.swp391.bookverse.exception;

/**
 * @Author huangdat
 */

public enum ErrorCode {
    // Standard HTTP error codes
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized error"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    // Custom error codes for the application
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception occurred"),
    USER_EXISTS(1001, "User already exists"),
    USER_NOT_FOUND(1002, "User not found"),
    NO_USERS_STORED(1003, "No users stored in database"),

    USERNAME_INVALID(1004, "Username must be at least 3 characters long"),
    PASSWORD_INVALID(1005, "Password must be at least 8 characters long"),
    NAME_INVALID(1006, "Name must be at least 3 characters long"),
    EMAIL_INVALID(1007, "Email must be a valid email address"),
    BIRTH_DATE_INVALID(1008, "Birth date must be in the past"),

    // -- Lỗi liên quan đến Category (SubCategory/SuperCategory) --
    CATEGORY_NOT_FOUND(2001, "Category not found"),
    SUPER_CATEGORY_NOT_FOUND(2002, "Super Category not found or invalid ID"),
    CATEGORY_NAME_DUPLICATE(2003, "Category name already exists"),

    INVALID_KEY(99999, "Invalid message key provided")
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
