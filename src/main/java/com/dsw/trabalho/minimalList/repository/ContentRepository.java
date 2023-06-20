package com.dsw.trabalho.minimalList.repository;

import com.dsw.trabalho.minimalList.model.Category;
import com.dsw.trabalho.minimalList.model.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    @Query(value = "SELECT * FROM contents WHERE name LIKE %?1% OR title LIKE %?1%", nativeQuery = true)
    List<Content> findAllByNameOrTitle(String search);

    List<Content> findAllBySeason(int season);

    @Query(value = "SELECT DISTINCT c.season FROM contents as c ORDER BY c.season", nativeQuery = true)
    List<Integer> findAllSeason();

    List<Content> findAllByCategory(Category category);

    @Query("SELECT c FROM Content c WHERE (:title is null or c.title LIKE %:title%) "
            + "AND (:name is null or c.name LIKE %:name%) "
            + "AND (:season is null or c.season = :season) "
            + "AND (:categoryId is null or c.category.id = :categoryId)")
    List<Content> searchContent(
            @Param("title") String title,
            @Param("name") String name,
            @Param("season") Integer season,
            @Param("categoryId") Integer categoryId);
}
