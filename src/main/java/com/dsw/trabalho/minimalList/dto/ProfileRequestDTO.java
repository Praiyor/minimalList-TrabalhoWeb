package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDTO {
    @NotNull(message = "id é obrigatório")
    private Integer id;
    @NotEmpty(message = "Nickname é obrigatório")
    private String nickname;
    @NotEmpty(message = "Descrição é obrigatório")
    private String description;
}


