package com.sparta.backoffice.user.dto;

import com.sparta.backoffice.user.entity.User;
import com.sparta.backoffice.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
