package com.dsw.trabalho.minimalList.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "content")
@ConfigurationProperties("api.url")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = true)
    @Nullable
    private String season;

    private String title;
    private String description;

    //@OneToMany
    //private List<Category> idCategory;

    private String produce;

    private Date date;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonProperty
    private LocalDateTime updatedAt;
}
