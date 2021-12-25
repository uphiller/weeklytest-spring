package com.sparta.weeklytestspring.controller;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.dto.*;
import com.sparta.weeklytestspring.security.UserDetailsImpl;
import com.sparta.weeklytestspring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;


@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @PostMapping("/article")
    public SetArticleDto.Response setArticle(@ModelAttribute SetArticleDto.Request articleRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        Article article = articleService.setArticle(articleRequestDto, userDetails.getUser());
        SetArticleDto.Response response = modelMapper.map(article, SetArticleDto.Response.class);
        return response;
    }

    @GetMapping("/articles")
    public Page<GetArticlesDto.Response> getArticles(@RequestParam(required = false) String searchTag, Pageable pageable){
        Page<Article> articles = articleService.getArticles(searchTag, pageable);
        Page<GetArticlesDto.Response> response = articles.map(new Function<Article, GetArticlesDto.Response>() {
            @Override
            public GetArticlesDto.Response apply(Article entity) {
                GetArticlesDto.Response dto = modelMapper.map(entity, GetArticlesDto.Response.class);
                return dto;
            }
        });
        return response;
    }

    @GetMapping("/article/{id}")
    public GetArticleDto.Response getArticle(@PathVariable Long id){
        Article article = articleService.getArticle(id);
        GetArticleDto.Response response = modelMapper.map(article, GetArticleDto.Response.class);
        return response;
    }


    @PostMapping("/article/comment")
    public void setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        articleService.setArticleComment(articleCommentRequestDto);
    }
}
