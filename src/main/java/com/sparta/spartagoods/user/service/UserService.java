package com.sparta.spartagoods.user.service;


import com.sparta.spartagoods.user.dto.SignupRequestDto;
import com.sparta.spartagoods.user.dto.UserResponseDto;
import com.sparta.spartagoods.user.entity.User;
import com.sparta.spartagoods.user.entity.UserRoleEnum;
import com.sparta.spartagoods.user.jwt.JwtUtil;
import com.sparta.spartagoods.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
//    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public UserResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String enterPassword = requestDto.getPassword();
        if (enterPassword == null || enterPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        String password = passwordEncoder.encode(enterPassword);
        String gender = requestDto.getGender();
        String phoneNumber = requestDto.getPhoneNumber();

        // email 중복확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(email, password, gender, phoneNumber, role);
        userRepository.save(user);
        return new UserResponseDto(user);
    }


    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 회원은 존재하지 않습니다.")
        );
    }
}