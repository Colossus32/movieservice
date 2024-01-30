package com.colossus.movieservice.service;

import com.colossus.movieservice.dto.UserEditingRequest;
import com.colossus.movieservice.dto.UserRegistrationRequest;
import com.colossus.movieservice.entity.User;
import com.colossus.movieservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public record UserService(UserRepo userRepo) {


    public ResponseEntity<?> saveUser(
            UserRegistrationRequest userRegistrationRequest) {

        log.info("{} is saving...", userRegistrationRequest);

        if (!checkUserRegistrationRequest(userRegistrationRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        User toSave = User.builder()
                .email(userRegistrationRequest.getEmail())
                .username(userRegistrationRequest.getUsername())
                .name(userRegistrationRequest.getName())
                .build();

        userRepo.save(toSave);
        return ResponseEntity.ok(userRepo.findByEmail(toSave.getEmail()));
    }

    private boolean checkUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        log.info("{} is checking for correct...", userRegistrationRequest);
        if (userRegistrationRequest == null) return false;

        return userRegistrationRequest.getEmail() != null &&
                userRegistrationRequest.getUsername() != null &&
                userRegistrationRequest.getUsername().matches("[a-zA-Z]+") &&
                userRepo.findByEmail(userRegistrationRequest.getEmail()).isEmpty() &&
                userRepo.findByUsername(userRegistrationRequest.getUsername()).isEmpty();
    }

    public ResponseEntity<?> getUserById(long id) {
        log.info("Getting user {} details", id);
        Optional<User> fromDB = userRepo.findById(id);
        if (fromDB.isPresent()) return ResponseEntity.ok(fromDB);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<?> editUserDetails(long id, UserEditingRequest request) {
        log.info("Editing user {} details with request:{}", id, request);
        Optional<User> fromDB = userRepo.findById(id);
        if (fromDB.isEmpty() || userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User toSave = fromDB.get();
        toSave.setUsername(request.getUsername());
        toSave.setName(request.getName());

        userRepo.save(toSave);
        return ResponseEntity.ok(toSave);
    }


    public void deleteUser(long id) {
        log.info("Deleting user {}", id);
        Optional<User> fromDB = userRepo.findById(id);
        if (fromDB.isPresent()) userRepo.deleteById(id);
    }
}
