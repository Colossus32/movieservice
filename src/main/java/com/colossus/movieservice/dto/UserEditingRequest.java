package com.colossus.movieservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserEditingRequest {

    private String username;
    private String name;
}
