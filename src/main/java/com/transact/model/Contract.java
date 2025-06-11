package com.transact.model;

import com.transact.enums.ContractStatus;
import com.transact.enums.PaymentTerms;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ContractTemplate template;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private String contractorName;

    private String contractTitle;

    private String clientName;

    private String contractDescription;

    private String scopeOfWork;

    private String deliverables;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @CreationTimestamp
    private Instant startDate;

    private LocalDateTime endDate;

    private String timelineDetails;

    private Long paymentAmount;

    @Enumerated(EnumType.STRING)
    private PaymentTerms paymentTerms;

    private String additionalTermsAndConditions;
}
