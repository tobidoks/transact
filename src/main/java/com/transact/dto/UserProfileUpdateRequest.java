package com.transact.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate dob;
}
