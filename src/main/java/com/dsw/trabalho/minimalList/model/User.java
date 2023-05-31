package com.dsw.trabalho.minimalList.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** User */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@ConfigurationProperties("api.url")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;

    @JsonIgnore
    private String password;

    @Column(nullable = true)
    @Nullable
    private String nickname;


    @Column(nullable = true)
    @Nullable
    private String image;

    @Column(nullable = true)
    @Nullable
    private String background;

    @Column(nullable = true)
    @Nullable
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonProperty
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany
    private List<UserLibrary> userLibrary;

    private String token;

    private String imagePath;

    public String getImagePathComplete() {
        String path = "http://localhost:8080/";
        
        return path + imagePath + "/"+ image;
    }

    public String getBackgroundPathComplete() {
        String path = "http://localhost:8080/";
        
        return path + imagePath + "/"+ background;
    }
}
