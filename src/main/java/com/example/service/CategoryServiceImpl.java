package com.example.service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByIsDelete(false);
    }

    public List<Category> getParentList() {
        return categoryRepository.findByParentAndIsDelete(null, false);
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);

    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            category.setDelete(true);
            categoryRepository.save(category);
        }
    }
}
