package com.sparta.weeklytestspring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class SetArticleDto {

    @Getter
    @Setter
    public static class Request {
        private String title;
        private String content;
        private String tags;
        private MultipartFile image;
    }

    @Getter
    @Setter
    public static class Response {
        private Long idx;
        private String title;
        private String content;
    }
}
