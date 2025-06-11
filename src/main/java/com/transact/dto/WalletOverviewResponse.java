package com.transact.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WalletOverviewResponse {
    private Long balance;
    private int totalTransactions;
    private List<String> connectedWalletAddresses;
}
