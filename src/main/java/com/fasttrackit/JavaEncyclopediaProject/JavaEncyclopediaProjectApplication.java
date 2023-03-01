package com.fasttrackit.JavaEncyclopediaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableJpaAuditing
public class JavaEncyclopediaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEncyclopediaProjectApplication.class, args);
    }

}
