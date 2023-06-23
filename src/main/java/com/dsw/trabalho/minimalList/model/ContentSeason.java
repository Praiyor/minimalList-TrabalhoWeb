package com.dsw.trabalho.minimalList.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contents_seasons")
public class ContentSeason {

    @Id
    @GeneratedValue
    private int id;

    private int episode;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "content_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Content content;

    @JsonBackReference
    public Content getContent() {
        return content;
    }
}
