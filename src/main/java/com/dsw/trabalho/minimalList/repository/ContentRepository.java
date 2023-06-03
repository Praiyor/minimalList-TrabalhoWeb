package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    List<Content> findByNameLike(String name);
}
