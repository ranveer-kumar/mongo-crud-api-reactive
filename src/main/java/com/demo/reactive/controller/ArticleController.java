package com.demo.reactive.controller;

import com.demo.reactive.error.ArticleNotFoundException;
import com.demo.reactive.model.Article;
import com.demo.reactive.model.ArticleInput;
import com.demo.reactive.model.ArticleMapper;
import com.demo.reactive.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static java.util.Comparator.comparing;

@RestController
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;

    @QueryMapping
    public Mono<Article> articleById(@Argument String id) {
        return this.articleRepository.findById(id)
                .switchIfEmpty(Mono.error(new ArticleNotFoundException(id)));
    }

    //TODO: ResponseEntity returning null fields with below method

//    @QueryMapping
//    public Mono<ResponseEntity<Article>> articleById(@Argument Long id) {
//        return articleRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
////        .switchIfEmpty(Mono.error(new ArticleNotFoundException(id)));
//    }

    @PutMapping("/article/{id}")
    private Mono<ResponseEntity<Article>> updateArticle(@PathVariable("id") String id, @RequestBody ArticleInput articleInput) {
        return articleRepository.findById(id)
                .flatMap(entity -> {
                    entity.setId(UUID.randomUUID().toString());
                    entity.setTitle(articleInput.getTitle());
                    entity.setMobileHeadline(articleInput.getMobileHeadline());
                    entity.setSummary(articleInput.getSummary());
                    entity.setElements(articleInput.getElements());
                   // entity.setMetaData(metaData);
                    entity.setType(articleInput.getType());
                    return this.articleRepository.save(entity);
                })
                .map(article1 ->
                        new ResponseEntity<>(article1, HttpStatus.OK)
                );
    }

    @MutationMapping
    public Mono<ResponseEntity<Article>> deleteArticle(@Argument String id) {

        return articleRepository.findById(id)
                .flatMap(article ->
                        articleRepository.delete(article)
                                .then(
                                        Mono.just(
                                                new ResponseEntity<Article>(article, HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @MutationMapping
    private Mono<Article> addArticle(@Argument Article articleInput) {
        articleInput.setId(UUID.randomUUID().toString());
        return this.articleRepository.save(articleInput);
    }

    //TODO: Pagination need
    @QueryMapping("allArticles")
    public Flux<Article> all(@Argument Integer page) {
        return findByIdNotNull(page)
                .sort(comparing(Article::getId).reversed())
                .skip(page * 10).take(10);
    }

    private Flux<Article> findByIdNotNull(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "MetaData.CreationDate");

        return this.articleRepository.findByIdNotNull()
//                .filter(p -> Status.PUBLISHED == p.getStatus())
//                .filter(
//                        p -> Optional.ofNullable(q)
//                                .map(key -> p.getTitle().contains(key) || p.getContent().contains(key))
//                                .orElse(true)
//                )
                .log();
    }
}
