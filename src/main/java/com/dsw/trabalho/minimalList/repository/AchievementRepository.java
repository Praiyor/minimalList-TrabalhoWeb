package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Achievement;
import com.dsw.trabalho.minimalList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Integer>
{

    Object findByUser(User user);
}
