package com.sparta.backoffice.user.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long id;
    private String email;
    private String password;
    private boolean manager;
}
