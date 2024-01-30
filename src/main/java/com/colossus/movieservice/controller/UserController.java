package com.colossus.movieservice.controller;

import com.colossus.movieservice.dto.UserEditingRequest;
import com.colossus.movieservice.dto.UserRegistrationRequest;
import com.colossus.movieservice.repository.UserRepo;
import com.colossus.movieservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public record UserController(UserService userService) {

    @PostMapping
    public ResponseEntity<?>userRegistration(
            UserRegistrationRequest userRegistrationRequest){

        return userService.saveUser(userRegistrationRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@RequestHeader("User-Id")long headerId,
                                            @PathVariable("id")long id){

        if (headerId != id) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUserDetails(@RequestHeader("User-Id")long headerId,
                                             @RequestBody UserEditingRequest request,
                                             @PathVariable long id) {

        if (headerId != id) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return userService.editUserDetails(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@RequestHeader("User-Id") long headerId,
                           @PathVariable long id){

        if (headerId == id) userService.deleteUser(id);
    }
}
