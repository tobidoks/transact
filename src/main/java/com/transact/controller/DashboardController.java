package com.transact.controller;

import com.transact.dto.DashboardOverviewResponse;
import com.transact.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "API for user dashboard overview")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Get dashboard overview", description = "Returns contract stats, recent activity, and quick actions for the user.")
    @GetMapping
    public ResponseEntity<DashboardOverviewResponse> getDashboard(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getDashboard(userId));
    }
}

