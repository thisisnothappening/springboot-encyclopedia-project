package com.fasttrackit.JavaEncyclopediaProject.article;

import com.fasttrackit.JavaEncyclopediaProject.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Column(length = 100000)
    private String picture;
    @Column(length = 100000)
    private String text;
    @Column(name = "created_at")
    @CreatedDate
    private Instant createdDate;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant lastModifiedDate;

    public Article(String name, Category category, String picture, String text) {
        this.name = name;
        this.category = category;
        this.picture = picture;
        this.text = text;
    }
}
