package com.sparta.weeklytestspring.controller;

import com.sparta.weeklytestspring.dto.JwtResponse;
import com.sparta.weeklytestspring.dto.UserDto;
import com.sparta.weeklytestspring.service.UserService;
import com.sparta.weeklytestspring.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    // 회원가입, 로그인 하나의 API 에서 처리
    @PostMapping("/login")
    public ResponseEntity<?> setArticle(UserDto userDto) throws IOException {
        if (userDto.getLoginCheck().equals("signup")) {
            userService.registerUser(userDto); // 사용자 등록
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

}