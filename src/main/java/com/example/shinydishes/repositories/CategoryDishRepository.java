package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryDish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDishRepository extends CrudRepository<CategoryDish, Long> {
    List<CategoryDish> findAll();
    List<CategoryDish> findByType(String type);
}
