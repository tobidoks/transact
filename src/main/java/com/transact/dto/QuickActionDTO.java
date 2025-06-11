package com.transact.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuickActionDTO {
    private String label;
    private String endpoint;
    private String icon; // optional: for UI
}

