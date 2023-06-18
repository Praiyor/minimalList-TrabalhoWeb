package com.dsw.trabalho.minimalList.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @NotEmpty(message = "Nickname é obrigatório")
    private String nickname;
    @NotEmpty(message = "Senha é obrigatório")
    private String password;
}


