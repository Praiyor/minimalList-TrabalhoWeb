package com.dsw.trabalho.minimalList.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.dsw.trabalho.minimalList.model.User;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  @Query(value = "SELECT * FROM users WHERE id = ?1 AND email = ?2 AND token = ?3 LIMIT 1", nativeQuery = true)
  Optional<User> findByAuthenticatedUser(int id, String email, String token);

}
