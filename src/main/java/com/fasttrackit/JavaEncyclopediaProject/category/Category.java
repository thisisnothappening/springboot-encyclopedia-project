package com.fasttrackit.JavaEncyclopediaProject.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasttrackit.JavaEncyclopediaProject.article.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    @Column
    private String name;
    @NotNull
    @JsonIgnore
    @OneToMany
    private List<Article> articleList = new ArrayList<>();

    public Category(@NotNull String name) {
        this.name = name;
    }
}
