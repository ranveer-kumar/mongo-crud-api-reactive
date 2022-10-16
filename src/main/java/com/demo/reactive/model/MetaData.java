package com.demo.reactive.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class MetaData{
    private LocalDateTime creationDate;
    private List<KeyValuePair> createdBy;
    private LocalDateTime updatedDate;
    private List<KeyValuePair> updatedBy;
    private LocalDateTime publishedDate;
    private List<KeyValuePair> publishedBy;
    private List<KeyValuePair> section;
    private List<KeyValuePair> subSection;
    private List<KeyValuePair> topic;
    private Boolean isDeleted;
    private List<KeyValuePair> readByUsers;
    private List<Integer> domainId;

}


