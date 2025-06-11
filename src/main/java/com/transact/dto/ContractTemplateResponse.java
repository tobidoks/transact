package com.transact.dto;

import com.transact.enums.ContractType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractTemplateResponse {
    private Long id;
    private String name;
    private String description;
    private String defaultDescription;
    private String defaultScopeOfWork;
    private String defaultDeliverables;
    private ContractType type;
}
