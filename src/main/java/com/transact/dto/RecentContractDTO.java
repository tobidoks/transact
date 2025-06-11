package com.transact.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecentContractDTO {
    private Long contractId;
    private String title;
    private String status;
    private String createdAt;
    private Long value;
}

