package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToOne
    private Category category;
    @Column(length = 100000)
    private String picture;
    @Column(length = 100000)
    private String text;

    public Article(String name, Category category, String picture, String text) {
        this.name = name;
        this.category = category;
        this.picture = picture;
        this.text = text;
    }
}
