package com.transact.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContractsListResponse {
    private List<ContractSummaryDTO> createdContracts;
    private List<ContractSummaryDTO> participatingContracts;
}
