package com.sparta.weeklytestspring.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String loginCheck;
    private String username;
    private String password;
}
