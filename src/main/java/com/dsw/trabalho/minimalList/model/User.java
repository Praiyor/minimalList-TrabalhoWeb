package com.dsw.trabalho.minimalList.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<UserLibrary> libraries;

    private String token;

    private String imagePath;

    public String getImagePathComplete() {
        String path = "http://localhost:8080";
        if (image  == null) return path + "/assets/images/default.png";

        return path + "/" + imagePath + "/"+ image;
    }

    public String getBackgroundPathComplete() {
        String path = "http://localhost:8080";
        if (background == null) return path + "/assets/images/background.png";

        return path + "/" + imagePath + "/"+ background;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @JsonManagedReference
    public List<Review> getReviews() {
        return reviews;
    }

    @JsonManagedReference
    public List<Achievement> getAchievements() {
        return achievements;
    }

    @JsonManagedReference
    public List<UserLibrary> getLibraries() {
        return libraries;
    }
}
