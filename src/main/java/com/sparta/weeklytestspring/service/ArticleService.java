package com.sparta.weeklytestspring.service;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.domain.Comment;
import com.sparta.weeklytestspring.domain.Tag;
import com.sparta.weeklytestspring.domain.User;
import com.sparta.weeklytestspring.dto.ArticleCommentRequestDto;
import com.sparta.weeklytestspring.dto.SetArticleDto;
import com.sparta.weeklytestspring.repository.ArticleRepository;
import com.sparta.weeklytestspring.repository.CommentRepository;
import com.sparta.weeklytestspring.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final AwsService awsService;

    @Transactional
    public Article setArticle(SetArticleDto.Request articleRequestDto, User user) throws IOException {
        String url = null;
        if(articleRequestDto.getImage() != null) url = awsService.upload(articleRequestDto.getImage());
        Article article = new Article(articleRequestDto, url, user);
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

    public Page<Article> getArticles(String searchTag, Pageable pageable){
        if(searchTag.isEmpty()){
            return articleRepository.findAll(pageable);
        } else {
            return articleRepository.findAllByTagsName(searchTag, pageable);
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
