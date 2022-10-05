package com.fasttrackit.JavaEncyclopediaProject.article;

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
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private String category;
    @Column(length = 100000)
    private String picture;
    @Column(length = 100000)
    private String text;

    public Article(String name) {
        this.name = name;
    }

    public Article(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Article(String name, String category, String picture) {
        this.name = name;
        this.category = category;
        this.picture = picture;
    }

    public Article(String name, String category, String picture, String text) {
        this.name = name;
        this.category = category;
        this.picture = picture;
        this.text = text;
    }
}
