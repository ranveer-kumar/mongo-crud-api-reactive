package com.demo.reactive.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articles")
@Data
public class Article {
    @Id
    private String id;
    private String title;
    private String mobileHeadline;
    private String summary;
    private String type;
    private MetaData metaData;
    private List<Integer> elements;

}




