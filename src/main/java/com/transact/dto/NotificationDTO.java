package com.transact.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    private String message;
    private String timestamp;
    private boolean read;
}

