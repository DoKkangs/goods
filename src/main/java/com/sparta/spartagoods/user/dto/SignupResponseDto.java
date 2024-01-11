package com.sparta.spartagoods.user.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long id;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
    private boolean manager;
}
