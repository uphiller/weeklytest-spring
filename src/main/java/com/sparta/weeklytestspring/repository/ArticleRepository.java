package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTagsName(String name);
}
