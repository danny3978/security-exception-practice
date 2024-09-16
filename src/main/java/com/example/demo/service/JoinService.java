package com.example.demo.service;

import com.example.demo.dto.request.JoinDto;
import com.example.demo.dto.response.JoinSuccess;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.UserDuplicationException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//form to of 정적 팩토리 메서드
// builder

@Service
@RequiredArgsConstructor
public class JoinService {

    private UserRole userRole = UserRole.ROLE_USER;
    private UserRole adminRole = UserRole.ROLE_ADMIN;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String adminPassword = "iahdiu!@#QWEaosidjas";

    public JoinSuccess joinProcess(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist) {
            throw new UserDuplicationException("중복된 사용자 입니다.");
        }

        User user = User.create(
                username,
                bCryptPasswordEncoder.encode(password),
                password.equals(adminPassword) ? adminRole : userRole
        );

       user = userRepository.save(user);

         return new JoinSuccess(user.getId(), "회원가입 성공", HttpStatus.CREATED.value());
    }

}
