package com.dsw.trabalho.minimalList.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ContentDTO {
    private Integer id;
    private String name;
    private String duration;
    private String season;
    private String title;
    private String description;
    private String produce;
    private Date date;
}
