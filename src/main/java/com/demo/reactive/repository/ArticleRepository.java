package com.demo.reactive.repository;

import com.demo.reactive.model.Article;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author ranveerkumar
 */
public interface ArticleRepository extends ReactiveCrudRepository<Article, Long> {
//Page<WebStory> findAllPageble(Pageable pageable);
//    Flux<WebStory> findByIdNotNull(Pageable pageable);
    Flux<Article> findByIdNotNull();

}
