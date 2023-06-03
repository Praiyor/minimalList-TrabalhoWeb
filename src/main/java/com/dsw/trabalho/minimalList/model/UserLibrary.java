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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private String episode;
    private String statusCotent;
    
    @OneToOne
    private Content content;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;
}

