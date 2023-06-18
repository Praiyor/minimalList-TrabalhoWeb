package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignInDTO {
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @NotEmpty(message = "Senha é obrigatório")
    private String password;
}
