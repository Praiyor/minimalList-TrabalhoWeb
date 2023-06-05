package com.dsw.trabalho.minimalList.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {
    private Integer id;
    private String name;
}
