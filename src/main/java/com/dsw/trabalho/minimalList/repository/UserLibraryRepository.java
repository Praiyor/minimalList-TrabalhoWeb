package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.model.UserLibrary;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibrary, Integer> {
    List<UserLibrary> findAllByUser(User user);

    Optional<UserLibrary> findByUserAndContent(User user, Content content);
}
