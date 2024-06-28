package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryFryingPan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryFryingPanRepository extends CrudRepository<CategoryFryingPan, Long> {
    List<CategoryFryingPan> findByType(String type);
}

