package com.transact.dto;

import com.transact.enums.ContractStatus;
import lombok.Data;

@Data
public class ContractStatusUpdateRequest {
    private Long contractId;
    private ContractStatus newStatus;
}
