package com.dsw.trabalho.minimalList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsw.trabalho.minimalList.model.ContentSeason;

@Repository
public interface ContentSeasonRepository extends JpaRepository<ContentSeason, Integer> {
}
