package com.transact.service;

import com.transact.dto.WalletLinkRequest;
import com.transact.dto.WalletOverviewResponse;
import com.transact.dto.WalletTransactionDTO;
import com.transact.model.Transaction;
import com.transact.model.User;
import com.transact.model.Wallet;
import com.transact.repository.TransactionRepository;
import com.transact.repository.UserRepository;
import com.transact.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public Wallet linkWallet(WalletLinkRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = Wallet.builder()
                .user(user)
                .address(request.getAddress())
                .type(request.getType())
                .build();

        return walletRepository.save(wallet);
    }

    public List<String> getConnectedWallets(Long userId) {
        return walletRepository.findByUserId(userId).stream()
                .map(Wallet::getAddress)
                .collect(Collectors.toList());
    }

    public WalletOverviewResponse getWalletOverview(Long userId) {
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        List<Transaction> transactions = transactionRepository.findByWalletUserIdOrderByTimestampDesc(userId);

        long totalBalance = transactions.stream()
                .mapToLong(tx -> "deposit".equals(tx.getType()) ? tx.getAmount() : -tx.getAmount())
                .sum();

        return WalletOverviewResponse.builder()
                .balance(totalBalance)
                .totalTransactions(transactions.size())
                .connectedWalletAddresses(wallets.stream().map(Wallet::getAddress).toList())
                .build();
    }


    public List<WalletTransactionDTO> getRecentTransactions(Long userId) {
        return transactionRepository.findByWalletUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(tx -> new WalletTransactionDTO(
                        tx.getTxHash(),
                        tx.getType(),
                        tx.getAmount(),
                        tx.getStatus(),
                        tx.getTimestamp().toString()
                ))
                .collect(Collectors.toList());
    }

}

