package com.transact.controller;

import com.transact.dto.ContractCreationRequest;
import com.transact.dto.ContractStatusUpdateRequest;
import com.transact.dto.ContractsListResponse;
import com.transact.model.Contract;
import com.transact.service.ContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@Tag(name = "Contract Controller", description = "APIs for managing contracts")
public class ContractController {

    private final ContractService contractService;

    @Operation(summary = "Create a new contract", description = "Creates a new contract between users based on the provided details.")
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody ContractCreationRequest request) {
        return ResponseEntity.ok(contractService.createContract(request));
    }

    @Operation(summary = "Update contract status", description = "Updates the status of an existing contract (e.g., to accepted, rejected, completed).")
    @PatchMapping("/status")
    public ResponseEntity<Contract> updateContractStatus(@RequestBody ContractStatusUpdateRequest request) {
        return ResponseEntity.ok(contractService.updateStatus(request.getContractId(), request.getNewStatus()));
    }
}
