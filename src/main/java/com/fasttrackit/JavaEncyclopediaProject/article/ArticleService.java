package com.fasttrackit.JavaEncyclopediaProject.article;


import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import com.fasttrackit.JavaEncyclopediaProject.category.CategoryRepository;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository; // I will remove this later

    // I will add CategoryService here after i fix all the bugs

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public Article postArticle(Article article) {
        if (categoryRepository.existsByName(article.getCategory().getName())) {
            article.setCategory(categoryRepository.findByName(article.getCategory().getName()));
        } else {
            article.setCategory(categoryRepository.save(new Category(article.getCategory().getName())));
        }
        return articleRepository.save(article);
    }

    public List<Article> getArticles(String name, String category) {
        return articleRepository.getAllFiltered(name, category);
    }

    public Article getArticleById(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
    }

    @Transactional
    public Article editArticle(Integer id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        existingArticle.setName(article.getName());
        existingArticle.setPicture(article.getPicture());
        existingArticle.setText(article.getText());
        if (categoryRepository.existsByName(article.getCategory().getName())) {
            existingArticle.setCategory(categoryRepository.findByName(article.getCategory().getName()));
        } else {
            existingArticle.setCategory(categoryRepository.save(article.getCategory()));
        }
        return articleRepository.save(existingArticle);
    }

    public void deleteArticle(Integer id) { // this slightly works but throws exception
       // articleRepository.delete(articleRepository.findById(id)
       //         .orElseThrow(() -> new ResourceNotFoundException("Article not found!")));
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        Category category = categoryRepository.findByName(article.getCategory().getName());
        articleRepository.delete(article);
        category.getArticleList().remove(article);
        categoryRepository.save(category);
        if (category.getArticleList().isEmpty()) {
            categoryRepository.delete(category);
        }
    }
}
