package com.transact.service;

import com.transact.dto.DashboardOverviewResponse;
import com.transact.dto.NotificationDTO;
import com.transact.dto.QuickActionDTO;
import com.transact.dto.RecentContractDTO;
import com.transact.enums.ContractStatus;
import com.transact.model.Contract;
import com.transact.repository.ContractRepository;
import com.transact.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ContractRepository contractRepository;
    private final NotificationRepository notificationRepository;

    public DashboardOverviewResponse getDashboard(Long userId) {
        List<Contract> userContracts = contractRepository.findByCreatorId(userId);

        LocalDate startOfMonth = YearMonth.now().atDay(1);

        int activeContracts = (int) userContracts.stream()
                .filter(c -> c.getStatus() == ContractStatus.ACTIVE)
                .count();

        int completedThisMonth = (int) userContracts.stream()
                .filter(c -> c.getStatus() == ContractStatus.COMPLETED &&
                        !c.getEndDate().isBefore(startOfMonth.atStartOfDay()))
                .count();

        long totalContractValue = userContracts.stream()
                .mapToLong(Contract::getPaymentAmount)
                .sum();

        List<RecentContractDTO> recentContracts = userContracts.stream()
                .sorted((a, b) -> b.getStartDate().compareTo(a.getStartDate()))
                .limit(5)
                .map(c -> RecentContractDTO.builder()
                        .contractId(c.getId())
                        .title(c.getContractTitle())
                        .status(String.valueOf(c.getStatus()))
                        .createdAt(c.getStartDate().toString())
                        .value(c.getPaymentAmount())
                        .build())
                .collect(Collectors.toList());

        List<QuickActionDTO> actions = List.of(
                QuickActionDTO.builder().label("Create Contract").endpoint("/contracts/create").icon("plus-circle").build(),
                QuickActionDTO.builder().label("Link Wallet").endpoint("/wallets/link").icon("link").build()
        );

        List<NotificationDTO> recentNotifications = notificationRepository.findTop5ByUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(n -> NotificationDTO.builder()
                        .message(n.getMessage())
                        .timestamp(n.getTimestamp().toString())
                        .read(n.isRead())
                        .build())
                .collect(Collectors.toList());

        return DashboardOverviewResponse.builder()
                .activeContracts(activeContracts)
                .totalContractValue(totalContractValue)
                .contractsCompletedThisMonth(completedThisMonth)
                .recentContracts(recentContracts)
                .quickActions(actions)
                .recentNotifications(recentNotifications)
                .build();
    }
}