package com.transact.service;

import com.transact.dto.UserProfileUpdateRequest;
import com.transact.dto.UserRegistrationDTO;
import com.transact.enums.KYCStatus;
import com.transact.model.User;
import com.transact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .kycStatus(KYCStatus.PENDING)
                .build();

        return userRepository.save(user);
    }
    public User updateUserProfile(Long userId, UserProfileUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        if (request.getDob() != null) user.setDob(String.valueOf(request.getDob()));

        return userRepository.save(user);
    }
    public User uploadKycDocuments(Long userId, MultipartFile govId, MultipartFile selfie) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            // Save files (for now: local disk — you can later use S3, etc.)
            String uploadDir = "uploads/kyc/" + userId;
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String govIdPath = uploadDir + "/govId_" + System.currentTimeMillis() + "_" + govId.getOriginalFilename();
            String selfiePath = uploadDir + "/selfie_" + System.currentTimeMillis() + "_" + selfie.getOriginalFilename();

            govId.transferTo(new File(govIdPath));
            selfie.transferTo(new File(selfiePath));

            user.setGovIdPath(govIdPath);
            user.setSelfiePath(selfiePath);
            user.setKycStatus(KYCStatus.UNDER_REVIEW);

            return userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading KYC files", e);
        }
    }
}
