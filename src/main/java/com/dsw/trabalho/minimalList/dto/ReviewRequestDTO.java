package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Integer idUser;
    private Integer idContent;

    @NotNull(message = "Avaliação é obrigatório")
    private Float rate;
    @NotEmpty(message = "Texto é obrigatório")
    @Size(min = 4)
    private String text;
    @NotEmpty(message = "Titulo é obrigatório")
    @Size(min = 4)
    private String title;
    private boolean spollier = false;
}
