package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.exceptions.NullFieldException;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article postArticle(Article article) {
        if (article.getName() == null || article.getCategory() == null || article.getPicture() == null || article.getText() == null) {
            throw new NullFieldException("Field cannot be null");
        }
        return articleRepository.save(article);
    }

    public List<Article> getArticles(String searchText, String selectCategory) {
        return articleRepository.getAllFiltered(searchText, selectCategory);
    }

   /* public List<Article> getArticles(String searchText) {
        if (searchText != null) {
            return articleRepository.findByName(searchText);
        }
        return articleRepository.findAll();
    }*/

    public Article getArticle(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
    }

    public Article editArticle(Integer id, Article article) {
        if (article.getName() == null || article.getCategory() == null || article.getPicture() == null || article.getText() == null) {
            throw new NullFieldException("Field cannot be null");
        }
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        existingArticle.setName(article.getName());
        existingArticle.setCategory(article.getCategory());
        existingArticle.setPicture(article.getPicture());
        existingArticle.setText(article.getText());
        return articleRepository.save(existingArticle);
    }

    public void deleteArticle(Integer id) {
        articleRepository.delete(articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!")));
    }
}
