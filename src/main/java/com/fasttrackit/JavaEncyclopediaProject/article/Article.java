package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column
    private String name;
    @NotNull
    @ManyToOne
    private Category category;
    @NotNull
    @Column(length = 100000)
    private String picture;
    @NotNull
    @Column(length = 100000)
    private String text;

    public Article(@NotNull String name, @NotNull Category category, @NotNull String picture, @NotNull String text) {
        this.name = name;
        this.category = category;
        this.picture = picture;
        this.text = text;
    }
}
