package com.transact.repository;

import com.transact.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findTop5ByUserIdOrderByTimestampDesc(Long userId);
}

