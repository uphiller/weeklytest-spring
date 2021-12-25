package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(attributePaths = {"comments","tags","user"})
    Page<Article> findAllByTagsName(String name, Pageable pageable);
    List<Article> findAllByTitleOrContent(String title, String content);

    @EntityGraph(attributePaths = {"comments","tags","user"})
    @Override
    Page<Article> findAll(Pageable pageable);
}
