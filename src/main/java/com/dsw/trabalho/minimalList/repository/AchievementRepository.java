package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Achievement;
import com.dsw.trabalho.minimalList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    Object findByUser(User user);
}
