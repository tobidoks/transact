package com.transact.dto;

import com.transact.enums.PaymentTerms;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractCreationRequest {
    private Long templateId;
    private Long contractorId;
    private String clientName;
    private String contractTitle;
    private String contractDescription;
    private String scopeOfWork;
    private String deliverables;
    private LocalDateTime endDate;
    private String timelineDetails;
    private Long paymentAmount;
    private PaymentTerms paymentTerms;
    private String additionalTermsAndConditions;
}

