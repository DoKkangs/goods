package com.sparta.spartagoods.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?])(?=.*[a-zA-Z0-9]).{8,15}$", message = "최소 8자 이상, 15자 이하의 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;
    private String gender;
    private String phoneNumber;
    private boolean admin;
//    private String adminToken = "";
}