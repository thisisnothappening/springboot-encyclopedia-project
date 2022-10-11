package com.fasttrackit.JavaEncyclopediaProject.category;

import com.fasttrackit.JavaEncyclopediaProject.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public void deleteCategory(Integer id) {
        categoryRepository.delete(categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!")));
    }
}
