package com.demo.reactive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesDTO {
    private List<Article> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public ArticlesDTO(Page<Article> webStoryPage){
        this.setData(webStoryPage.getContent());
        this.setTotalElements(webStoryPage.getTotalElements());
        this.setTotalPages(webStoryPage.getTotalPages());
        this.setCurrentPage(webStoryPage.getNumber() + 1);
        this.setFirst(webStoryPage.isFirst());
        this.setLast(webStoryPage.isLast());
        this.setHasNext(webStoryPage.hasNext());
        this.setHasPrevious(webStoryPage.hasPrevious());

    }


}
