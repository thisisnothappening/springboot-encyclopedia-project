package com.fasttrackit.JavaEncyclopediaProject.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("""
    SELECT a FROM Article a WHERE (?1 IS NULL OR a.name LIKE ?1)
    AND (?2 IS NULL OR a.category LIKE ?2)""")
    List<Article> getAllFiltered(String searchText, String selectCategory);

    // List<Article> findByName(String searchText);
}
