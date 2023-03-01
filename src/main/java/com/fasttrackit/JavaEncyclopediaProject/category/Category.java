package com.fasttrackit.JavaEncyclopediaProject.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasttrackit.JavaEncyclopediaProject.article.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Article> articleList = new ArrayList<>();
    @Column(name = "created_at")
    @CreatedDate
    private Instant createdDate;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant lastModifiedDate;

    public Category(String name) {
        this.name = name;
    }
}
