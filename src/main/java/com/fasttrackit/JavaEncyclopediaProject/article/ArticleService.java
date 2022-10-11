package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.CategoryRepository;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.NullFieldException;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public Article postArticle(Article article) {
        if (article.getName() == null || article.getCategory().getName() == null || article.getPicture() == null || article.getText() == null) {
            throw new NullFieldException("Field cannot be null");
        }
        if (categoryRepository.existsByName(article.getCategory().getName())) {
            article.setCategory(categoryRepository.findByName(article.getCategory().getName()));
        }
        return articleRepository.save(article);
    }

    public List<Article> getArticles(String name, String category) {
        return articleRepository.getAllFiltered(name, category);
    }

    public Article getArticle(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
    }

    @Transactional
    public Article editArticle(Integer id, Article article) {
        if (article.getName() == null || article.getCategory() == null || article.getPicture() == null || article.getText() == null) {
            throw new NullFieldException("Field cannot be null");
        }
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        existingArticle.setName(article.getName());
        existingArticle.setPicture(article.getPicture());
        existingArticle.setText(article.getText());
        if (categoryRepository.existsByName(article.getCategory().getName())) {
            existingArticle.setCategory(categoryRepository.findByName(article.getCategory().getName()));
        } else {
            existingArticle.setCategory(article.getCategory());
        }
        return articleRepository.save(existingArticle);
    }

    public void deleteArticle(Integer id) {
        articleRepository.delete(articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!")));
    }
}
