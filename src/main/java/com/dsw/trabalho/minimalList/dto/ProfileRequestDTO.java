package com.dsw.trabalho.minimalList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
public class ProfileRequestDTO {
    private Integer id;
    private String nickname;
    private String password;
    private String description;
}


