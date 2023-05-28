package com.dsw.trabalho.minimalList.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** User */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_libraries")
public class UserLibrary {
    @Id
    @GeneratedValue
    private Integer id;

    // id_content
    
    // id_review
    @ManyToOne
    @JoinColumn(name = "id_review", referencedColumnName="id", nullable = true)
    private Review review;

    // id_user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    private String episode;
    private String status_cotent;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;
}

