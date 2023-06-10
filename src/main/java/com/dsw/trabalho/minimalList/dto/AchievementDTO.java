package com.dsw.trabalho.minimalList.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AchievementDTO
{
    private Integer idUser;
    private String name;
    private boolean status;
}
