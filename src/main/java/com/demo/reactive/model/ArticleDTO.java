package com.demo.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private String id;
    private String title;
    private String mobileHeadline;
    private String summary;
    private String type;
    private MetaData metaData;
    private List<Integer> elements;

}
