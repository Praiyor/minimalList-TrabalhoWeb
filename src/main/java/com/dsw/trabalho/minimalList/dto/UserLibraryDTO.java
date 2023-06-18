package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class  UserLibraryDTO {
    private Integer idUser;
    private Integer idContent;
    @NotEmpty(message = "Episódio é obrigatório")
    private String episode;
    @NotEmpty(message = "Status é obrigatório")
    private String statusContent;
}

