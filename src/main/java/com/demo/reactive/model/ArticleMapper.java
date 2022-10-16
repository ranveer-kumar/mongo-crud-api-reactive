package com.demo.reactive.model;

import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDTO toDTO(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getMobileHeadline(),
                article.getSummary(),
                article.getType(),
                article.getMetaData(),
                article.getElements()

        );

    }
}
