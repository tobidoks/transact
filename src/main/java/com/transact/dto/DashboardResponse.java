package com.transact.dto;

import com.transact.enums.KYCStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {
    private Long userId;
    private String email;
    private int totalContracts;
    private int signedContracts;
    private KYCStatus kycStatus;
    private String walletAddress;
}
