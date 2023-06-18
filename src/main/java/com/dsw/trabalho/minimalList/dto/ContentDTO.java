package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ContentDTO {
    @NotNull(message = "id é obrigatório")
    private Integer id;
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    @NotEmpty(message = "Duração é obrigatório")
    private String duration;
    @NotNull(message = "Season é obrigatório")
    private int season;
    @NotEmpty(message = "Titulo é obrigatório")
    private String title;
    @NotEmpty(message = "Descrição é obrigatório")
    private String description;
    @NotEmpty(message = "Produtor é obrigatório")
    private String produce;
    @NotNull(message = "Data é obrigatório")
    private Date date;
}
