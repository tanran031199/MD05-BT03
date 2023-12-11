package com.example.service;

import com.example.entity.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    void delete(Integer id);
    List<Category> getParentList();
}
