package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryPots;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryPotsRepository extends CrudRepository<CategoryPots, Long> {
    List<CategoryPots> findByType(String type);
}
