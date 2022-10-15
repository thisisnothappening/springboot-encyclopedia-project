package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import com.fasttrackit.JavaEncyclopediaProject.category.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

@Configuration
public class ArticleReader {
    @Value("${file.articles}")
    private String fileArticlesPath;
    @Value("${file.articles.improved}")
    private String fileArticlesPathImproved;

    @SneakyThrows
    private void wikipediaReferenceBracketsRemover() {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArticlesPath));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileArticlesPathImproved));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line.replaceAll("\\[(.*?)]", ""));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }

    @Bean
    @SneakyThrows
    List<Article> readFile(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        wikipediaReferenceBracketsRemover();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArticlesPathImproved));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split(" \\| ");
            Article article;
            Category category;
            if (categoryRepository.existsByName(tokens[1])) {
                category = categoryRepository.findByName(tokens[1]);
                article = new Article(tokens[0], category, tokens[2], tokens[3]);
            } else {
                category = new Category(tokens[1]);
                categoryRepository.save(category);
                article = new Article(tokens[0], category, tokens[2], tokens[3]);
            }
            articleRepository.save(article);
        }
        bufferedReader.close();
        return articleRepository.findAll();
    }
}
