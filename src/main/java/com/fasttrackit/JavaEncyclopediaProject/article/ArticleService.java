package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import com.fasttrackit.JavaEncyclopediaProject.category.CategoryService;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.NullFieldException;
import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;
    }

    private void notNullOrBlank(Article article) {
        if (article.getName() == null || article.getName().trim().length() == 0 ||
                article.getCategory() == null ||
                article.getCategory().getName() == null || article.getCategory().getName().trim().length() == 0 ||
                article.getPicture() == null || article.getPicture().trim().length() == 0 ||
                article.getText() == null || article.getText().trim().length() == 0) {
            throw new NullFieldException("Field cannot be blank or null.");
        }
    }

    public Article postArticle(Article article) {
        notNullOrBlank(article);
        if (categoryService.existsByName(article.getCategory().getName())) {
            article.setCategory(categoryService.getCategoryByName(article.getCategory().getName()));
        } else {
            article.setCategory(categoryService.saveCategory(new Category(article.getCategory().getName())));
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
        notNullOrBlank(article);
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        existingArticle.setName(article.getName());
        existingArticle.setPicture(article.getPicture());
        existingArticle.setText(article.getText());
        Category category = categoryService.getCategoryByName(existingArticle.getCategory().getName());
        if (categoryService.existsByName(article.getCategory().getName())) {
            existingArticle.setCategory(categoryService.getCategoryByName(article.getCategory().getName()));
        } else {
            existingArticle.setCategory(categoryService.saveCategory(article.getCategory()));
        }
        if (!existingArticle.getCategory().getName().equals(category.getName())) {
            category.getArticleList().remove(existingArticle);
            categoryService.saveCategory(category);
        }
        if (category.getArticleList().isEmpty()) {
            categoryService.delete(category);
        }
        return articleRepository.save(existingArticle);
    }

    public void deleteArticle(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found!"));
        Category category = categoryService.getCategoryByName(article.getCategory().getName());
        articleRepository.delete(article);
        category.getArticleList().remove(article);
        categoryService.saveCategory(category);
        if (category.getArticleList().isEmpty()) {
            categoryService.delete(category);
        }
    }
}
