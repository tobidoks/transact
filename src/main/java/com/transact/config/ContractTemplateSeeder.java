package com.transact.config;

import com.transact.enums.ContractType;
import com.transact.model.ContractTemplate;
import com.transact.repository.ContractTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractTemplateSeeder implements CommandLineRunner {

    private final ContractTemplateRepository templateRepository;

    @Override
    public void run(String... args) {
        if (templateRepository.count() == 0) {
            List<ContractTemplate> templates = List.of(
                    ContractTemplate.builder()
                            .name("General Contract")
                            .description("Standard contract for various business agreements.")
                            .type(ContractType.GENERAL)
                            .build(),
                    ContractTemplate.builder()
                            .name("Rental Agreement")
                            .description("Property rental agreements and lease contracts.")
                            .type(ContractType.RENTAL)
                            .build(),
                    ContractTemplate.builder()
                            .name("Service Agreement")
                            .description("Professional service contracts and work agreements.")
                            .type(ContractType.SERVICE)
                            .build(),
                    ContractTemplate.builder()
                            .name("NDA")
                            .description("Non-disclosure agreement for confidential information.")
                            .type(ContractType.NDA)
                            .build(),
                    ContractTemplate.builder()
                            .name("Consulting Agreement")
                            .description("Professional consulting services contracts")
                            .type(ContractType.CONSULTING)
                            .build()
            );

            templateRepository.saveAll(templates);
        }
    }
}

