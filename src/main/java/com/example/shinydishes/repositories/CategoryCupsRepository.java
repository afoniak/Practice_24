package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryCups;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryCupsRepository extends CrudRepository<CategoryCups, Long> {
    List<CategoryCups> findByType(String type);
}
