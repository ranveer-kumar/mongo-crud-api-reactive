package com.demo.reactive.error;

public class ArticleNotFoundException  extends RuntimeException{
    public ArticleNotFoundException(Long id) {
            super("Article #" + id + " was not found.");
    }
}
