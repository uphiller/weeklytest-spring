package com.sparta.weeklytestspring.service;

import com.sparta.weeklytestspring.domain.User;
import com.sparta.weeklytestspring.dto.UserDto;
import com.sparta.weeklytestspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User registerUser(UserDto userDto) {
        // 회원가입 시 nickname은 username으로 설정
        // 비밀번호 인코딩
        String id = userDto.getUsername();
        String password = passwordEncoder.encode(userDto.getPassword());

        User user = new User(id, password);
        userRepository.save(user);
        return user;
    }


}
