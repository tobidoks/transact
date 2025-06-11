package com.transact.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
public class ContractSummaryDTO {
    private Long id;
    private String contractTitle;
    private String status;
    private LocalDateTime createdAt;
}
