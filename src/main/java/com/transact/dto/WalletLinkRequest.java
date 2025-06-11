package com.transact.dto;

import com.transact.enums.WalletType;
import lombok.Data;

@Data
public class WalletLinkRequest {
    private Long userId;
    private String address;
    private WalletType type; // WALLET or MAGIC
}
