package com.dsw.trabalho.minimalList.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    private Integer idUser;
    private Integer idContent;
    private Float rate;
    private String text;
    private boolean spollier;
}
