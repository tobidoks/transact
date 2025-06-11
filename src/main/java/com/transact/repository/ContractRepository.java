package com.transact.repository;

import com.transact.model.Contract;
import com.transact.enums.ContractStatus;
import com.transact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    int countByCreatorId(Long userId);
    int countByCreatorIdAndStatus(Long creator_id, ContractStatus status);
    List<Contract> findByCreatorId(Long creatorId);
}
