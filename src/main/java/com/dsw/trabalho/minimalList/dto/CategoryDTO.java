package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {
    @NotNull(message = "id é obrigatório")
    private Integer id;
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
}
