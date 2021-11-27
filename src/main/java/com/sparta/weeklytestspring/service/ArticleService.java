package com.sparta.weeklytestspring.service;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.domain.Comment;
import com.sparta.weeklytestspring.domain.Tag;
import com.sparta.weeklytestspring.dto.ArticleCommentRequestDto;
import com.sparta.weeklytestspring.dto.ArticleRequestDto;
import com.sparta.weeklytestspring.repository.ArticleRepository;
import com.sparta.weeklytestspring.repository.CommentRepository;
import com.sparta.weeklytestspring.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Article setArticle(ArticleRequestDto articleRequestDto){

        Article article = new Article(articleRequestDto);
        articleRepository.save(article);

        List<String> items = Arrays.asList(articleRequestDto.getTags().split("\\s*,\\s*"));
        List<Tag> tags = items.stream().map(tag -> new Tag(tag, article)).collect(Collectors.toList());
        tagRepository.saveAll(tags);

        return article;
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Article> getArticles(String searchTag){
        if(searchTag.isEmpty()){
            return articleRepository.findAll();
        } else {
            return articleRepository.findAllByTagsName(searchTag);
        }
    }

    @Transactional
    public void setArticleComment(ArticleCommentRequestDto articleCommentRequestDto){
        Article article = articleRepository.findById(articleCommentRequestDto.getIdx()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(articleCommentRequestDto, article);
        commentRepository.save(comment);
    }
}
