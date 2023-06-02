package com.dsw.trabalho.minimalList.dto;

import java.util.UUID;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    private Integer idUser;
    private UUID idContent;
    private Float rate;
    private String text;
    private boolean spollier;
}
