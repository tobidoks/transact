package com.transact.model;


import com.transact.enums.ContractType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Lob;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractTemplate {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // e.g. "General Contract", "Service Agreement"
    private String description; // e.g. "Standard contract template for various business agreements"

    @Lob
    private String defaultDescription;

    @Lob
    private String defaultScopeOfWork;

    @Lob
    private String defaultDeliverables;

    @Enumerated(EnumType.STRING)
    private ContractType type;
}
