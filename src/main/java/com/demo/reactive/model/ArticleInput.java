package com.demo.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticleInput {
    private String title;
    private String mobileHeadline;
    private String summary;
    private String type;
    private MetaData metaDataInput;
    private List<Integer> elements;


}
