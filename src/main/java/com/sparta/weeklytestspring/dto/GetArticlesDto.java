package com.sparta.weeklytestspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class GetArticlesDto {

    @Getter
    @Setter
    public static class Response {
        private Long idx;
        private String title;
        private String content;
        private Set<Tag> tags;
        private List<Comment> comments;
        private User user;
        private LocalDateTime createdAt;

    }

    @Getter
    @Setter
    public static class Tag {
        private Long idx;
        private String name;
    }

    @Getter
    @Setter
    public static class Comment {
        private Long idx;
        private String comment;
    }

    @Getter
    @Setter
    public static class User {
        private String username;
    }
}
