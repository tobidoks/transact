package com.transact.controller;

import com.transact.dto.ContractTemplateResponse;
import com.transact.service.ContractTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
@Tag(name = "Contract Template Controller", description = "APIs for managing contract templates")
public class ContractTemplateController {

    private final ContractTemplateService templateService;

    @Operation(
            summary = "Get all contract templates",
            description = "Retrieves a list of all predefined contract templates available for use."
    )
    @GetMapping
    public ResponseEntity<List<ContractTemplateResponse>> getTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
}
