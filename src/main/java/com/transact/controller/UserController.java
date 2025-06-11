package com.transact.controller;

import com.transact.dto.UserProfileUpdateRequest;
import com.transact.dto.UserRegistrationDTO;
import com.transact.model.User;
import com.transact.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user with the given profile details.")
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO dto) {
        User user = userService.registerUser(dto);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Update user profile", description = "Updates profile information for the given user ID.")
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUserProfile(
            @PathVariable Long id,
            @RequestBody UserProfileUpdateRequest request
    ) {
        User updatedUser = userService.updateUserProfile(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Upload KYC documents", description = "Uploads government ID and selfie for KYC verification of a user.")
    @PostMapping("/{id}/kyc")
    public ResponseEntity<User> uploadKyc(
            @PathVariable Long id,
            @RequestParam("govId") MultipartFile govId,
            @RequestParam("selfie") MultipartFile selfie
    ) {
        User user = userService.uploadKycDocuments(id, govId, selfie);
        return ResponseEntity.ok(user);
    }
}
