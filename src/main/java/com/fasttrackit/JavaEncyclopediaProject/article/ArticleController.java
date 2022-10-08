package com.fasttrackit.JavaEncyclopediaProject.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    Article postArticle(@RequestBody Article article) {
        return articleService.postArticle(article);
    }

    @GetMapping
    List<Article> getArticles(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String category) {
        return articleService.getArticles(name, category);
    }

    @GetMapping("{id}")
    Article getArticle(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @PutMapping("{id}")
    Article editArticle(@PathVariable Integer id, @RequestBody Article article) {
        return articleService.editArticle(id, article);
    }

    @DeleteMapping("{id}")
    void deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
    }
}
