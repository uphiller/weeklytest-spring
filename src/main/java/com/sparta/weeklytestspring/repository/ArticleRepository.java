package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
