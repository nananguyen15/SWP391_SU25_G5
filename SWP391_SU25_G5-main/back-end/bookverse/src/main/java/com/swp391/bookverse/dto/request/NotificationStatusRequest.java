package com.swp391.bookverse.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO request de cap nhat trang thai thong bao
 */
@Getter
@Setter
public class NotificationStatusRequest {
    private Boolean isRead;
}
