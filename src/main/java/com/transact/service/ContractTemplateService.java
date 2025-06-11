package com.transact.service;

import com.transact.dto.ContractTemplateResponse;
import com.transact.model.ContractTemplate;
import com.transact.repository.ContractTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractTemplateService {

    private final ContractTemplateRepository repository;

    public List<ContractTemplateResponse> getAllTemplates() {
        return repository.findAll().stream()
                .map(template -> ContractTemplateResponse.builder()
                        .id(template.getId())
                        .name(template.getName())
                        .description(template.getDescription())
                        .defaultDescription(template.getDefaultDescription())
                        .defaultScopeOfWork(template.getDefaultScopeOfWork())
                        .defaultDeliverables(template.getDefaultDeliverables())
                        .type(template.getType())
                        .build())
                .collect(Collectors.toList());
    }
}
