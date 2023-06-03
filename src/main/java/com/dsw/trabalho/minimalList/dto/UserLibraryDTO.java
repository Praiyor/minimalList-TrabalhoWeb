package com.dsw.trabalho.minimalList.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class  UserLibraryDTO {
    private Integer idUser;
    private Integer idContent;
    private String episode;
    private String statusContent;
}

