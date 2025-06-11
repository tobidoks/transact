package com.transact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WalletTransactionDTO {
    private String txHash;
    private String type;
    private Long amount;
    private String status;
    private String date;
}
