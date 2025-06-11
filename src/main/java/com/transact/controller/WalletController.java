package com.transact.controller;

import com.transact.dto.WalletLinkRequest;
import com.transact.dto.WalletOverviewResponse;
import com.transact.dto.WalletTransactionDTO;
import com.transact.model.Wallet;
import com.transact.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet Controller", description = "Endpoints for linking and viewing blockchain wallets and transactions")
public class WalletController {

    private final WalletService walletService;

    @Operation(
            summary = "Link a wallet to a user",
            description = "Associates a blockchain wallet address with an existing user account. " +
                    "Supports multiple wallets per user."
    )
    @PostMapping("/link")
    public ResponseEntity<Wallet> linkWallet(@RequestBody WalletLinkRequest request) {
        return ResponseEntity.ok(walletService.linkWallet(request));
    }

    @Operation(
            summary = "Get wallet overview",
            description = "Summary of all wallets connected to the user, including total balance."
    )
    @GetMapping("/overview")
    public ResponseEntity<WalletOverviewResponse> walletOverview(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getWalletOverview(userId));
    }

    @Operation(
            summary = "Get recent wallet transactions",
            description = "Most recent transactions performed across all wallets linked to the user."
    )
    @GetMapping("/transactions")
    public ResponseEntity<List<WalletTransactionDTO>> recentTransactions(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getRecentTransactions(userId));
    }

    @Operation(
            summary = "Get connected wallet addresses",
            description = "Wallet addresses currently linked to the specified user."
    )
    @GetMapping("/connected")
    public ResponseEntity<List<String>> connectedWallets(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getConnectedWallets(userId));
    }
}
