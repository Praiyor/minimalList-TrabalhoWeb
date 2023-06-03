package com.dsw.trabalho.minimalList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.User;
import com.dsw.trabalho.minimalList.model.UserLibrary;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibrary, Integer> {
    List<UserLibrary> findAllByUser(User user);
    UserLibrary findByUserAndContent(User user, Content content);

}
