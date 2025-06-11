package com.transact.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardOverviewResponse {
    private int activeContracts;
    private long totalContractValue;
    private int contractsCompletedThisMonth;
    private List<RecentContractDTO> recentContracts;
    private List<QuickActionDTO> quickActions;
    private List<NotificationDTO> recentNotifications;
}
