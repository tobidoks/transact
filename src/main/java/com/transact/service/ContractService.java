package com.transact.service;

import com.transact.dto.ContractCreationRequest;
import com.transact.dto.ContractSummaryDTO;
import com.transact.dto.ContractsListResponse;
import com.transact.model.Contract;
import com.transact.enums.ContractStatus;
import com.transact.model.ContractTemplate;
import com.transact.model.User;
import com.transact.repository.ContractRepository;
import com.transact.repository.ContractTemplateRepository;
import com.transact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractTemplateRepository templateRepository;
    private final UserRepository userRepository;

    public Contract createContract(ContractCreationRequest request) {
        ContractTemplate template = templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new RuntimeException("Template not found"));

        User creator = userRepository.findById((request.getContractorId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contract contract = Contract.builder()
                .template(template)
                .contractorName(creator.getFirstName())
                .clientName(request.getClientName())
                .contractTitle(request.getContractTitle())
                .contractDescription(
                        request.getContractDescription() != null ? request.getContractDescription() : template.getDefaultDescription()
                )
                .scopeOfWork(
                        request.getScopeOfWork() != null ? request.getScopeOfWork() : template.getDefaultScopeOfWork()
                )
                .deliverables(
                        request.getDeliverables() != null ? request.getDeliverables() : template.getDefaultDeliverables()
                )
                .endDate(request.getEndDate())
                .timelineDetails(request.getTimelineDetails())
                .paymentAmount(request.getPaymentAmount())
                .paymentTerms(request.getPaymentTerms())
                .additionalTermsAndConditions(request.getAdditionalTermsAndConditions())
                .status(ContractStatus.DRAFT)
                .build();

        return contractRepository.save(contract);
    }


    public Contract updateStatus(Long contractId, ContractStatus newStatus) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        contract.setStatus(newStatus);
        return contractRepository.save(contract);
    }

    private ContractSummaryDTO toSummary(Contract contract) {
        return ContractSummaryDTO.builder()
                .id(contract.getId())
                .contractTitle(contract.getContractTitle())
                .status(String.valueOf(contract.getStatus()))
                .createdAt(contract.getStartDate() != null ?
                        LocalDateTime.ofInstant(contract.getStartDate(), java.time.ZoneId.systemDefault())
                        : null)
                .build();
    }

}
