package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.Review;
import com.dsw.trabalho.minimalList.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllReviewsByUser(User user);
    List<Review> findAllByContent(Content content);
    Optional<Review> findByUserAndContent(User user, Content content);
    Optional<Review> findByUserAndContent(Integer userId, Integer contentId);
}
