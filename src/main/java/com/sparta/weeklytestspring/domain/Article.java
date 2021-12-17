package com.sparta.weeklytestspring.domain;

import com.sparta.weeklytestspring.dto.SetArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy="article")
    private List<Comment> comments;

    @OneToMany(mappedBy="article")
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name="user_idx", nullable = false)
    private User user;

    public Article(SetArticleDto.Request requestDto, String imageUrl, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.imageUrl = imageUrl;
        this.user = user;
    }
}
