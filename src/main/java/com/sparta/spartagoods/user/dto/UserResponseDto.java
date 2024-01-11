package com.sparta.spartagoods.user.dto;


import com.sparta.spartagoods.user.entity.User;
import com.sparta.spartagoods.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
    }
}
