package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(attributePaths = {"comments","tags"})
    List<Article> findAllByTagsName(String name);
    List<Article> findAllByTitleOrContent(String title, String content);

    @EntityGraph(attributePaths = {"comments","tags"})
    @Override
    List<Article> findAll();
}
