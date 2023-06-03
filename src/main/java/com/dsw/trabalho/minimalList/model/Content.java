package com.dsw.trabalho.minimalList.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = true)
    @Nullable
    private int season;

    @Column(nullable = true)
    private String image;

    private String title;

    @Column(columnDefinition="text")
    private String description;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews; 
    //@OneToMany
    //private List<Category> idCategory;

    private boolean produce;

    private LocalDate date;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonProperty
    private LocalDateTime updatedAt;
}
