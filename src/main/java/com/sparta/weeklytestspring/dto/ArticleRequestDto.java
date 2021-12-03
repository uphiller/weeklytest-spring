package com.sparta.weeklytestspring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ArticleRequestDto {
    private String title;
    private String content;
    private String tags;
    private MultipartFile image;
}
