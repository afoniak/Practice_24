package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryGlasses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryGlassesRepository extends CrudRepository<CategoryGlasses, Long> {
    List<CategoryGlasses> findByType(String type);
}

