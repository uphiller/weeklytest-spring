package com.sparta.weeklytestspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Tag extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="article_idx", nullable = false)
    private Article article;

    public Tag(String name, Article article) {
        this.name = name;
        this.article = article;
    }

    public Tag(String name) {
        this.name = name;
    }

}
