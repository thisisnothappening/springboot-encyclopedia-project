package com.fasttrackit.JavaEncyclopediaProject.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasttrackit.JavaEncyclopediaProject.article.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Article> articleList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
