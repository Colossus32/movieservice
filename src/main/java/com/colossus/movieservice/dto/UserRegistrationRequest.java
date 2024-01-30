package com.colossus.movieservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserRegistrationRequest {

    private String email;
    private String username;
    private String name;
}
